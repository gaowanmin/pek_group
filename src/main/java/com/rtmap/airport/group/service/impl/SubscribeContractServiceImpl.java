package com.rtmap.airport.group.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rtmap.airport.group.conf.RedisUtils;
import com.rtmap.airport.group.entity.BasAirport;
import com.rtmap.airport.group.entity.Flight;
import com.rtmap.airport.group.entity.SubScribeSimple;
import com.rtmap.airport.group.entity.SubscribeContract;
import com.rtmap.airport.group.entity.cond.SubScribeCond;
import com.rtmap.airport.group.enums.FltStatusEnum;
import com.rtmap.airport.group.enums.RedisKeyConstant;
import com.rtmap.airport.group.mapper.BasAirportMapper;
import com.rtmap.airport.group.mapper.FlightMapper;
import com.rtmap.airport.group.mapper.SubscribeContractMapper;
import com.rtmap.airport.group.result.CommonResponse;
import com.rtmap.airport.group.result.ResponseStatusEnum;
import com.rtmap.airport.group.service.ISubscribeContractService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rtmap.airport.group.util.DateUtils;
import com.rtmap.airport.group.vo.FltVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 订阅契约表 服务实现类
 * </p>
 *
 * @author gaowm
 * @since 2022-03-03
 */
@Service
public class SubscribeContractServiceImpl extends ServiceImpl<SubscribeContractMapper, SubscribeContract> implements ISubscribeContractService {

    @Resource
    private SubscribeContractMapper subscribeContractMapper;

    @Resource
    private FlightMapper flightMapper;

    @Resource
    private BasAirportMapper basAirportMapper;

    @Resource
    private RedisUtils redisUtils;

    @Override
    public CommonResponse subScribeFlt(SubscribeContract subscribeContract) {
        int row = 0;
        if (subscribeContract.getOpendid() == null)
            return new CommonResponse(ResponseStatusEnum.SubcribeIdNotEmpty);
        SubscribeContract db = subscribeContractMapper.selectOne(new QueryWrapper<SubscribeContract>().eq("opendid", subscribeContract.getOpendid()).eq("ffid", subscribeContract.getFfid()).lt("invalid_time", LocalDateTime.now()));
        if (db != null) {
            if ("Y".equals(db.getSubscribeStatus()))
                return new CommonResponse(ResponseStatusEnum.RepeatSubcribe);
            else {
                db.setSubscribeStatus("Y");
                db.setCreateTime(LocalDateTime.now());
                db.setInvalidTime(LocalDateTime.now().plusDays(2));
                row = subscribeContractMapper.update(db, new QueryWrapper<SubscribeContract>().eq("contract_id", db.getContractId()));
                return new CommonResponse(row == 1 ? true : false);
            }

        } else {
            subscribeContract.setCreateTime(LocalDateTime.now());
            subscribeContract.setInvalidTime(LocalDateTime.now().plusDays(2));
            row = subscribeContractMapper.insert(subscribeContract);
        }

        return new CommonResponse(row == 1 ? true : false);
    }

    @Override
    public CommonResponse getFltBySubScribe(SubScribeCond subScribeCond) {
        if (subScribeCond.getOpenid() == null)
            return new CommonResponse(ResponseStatusEnum.SubcribeIdNotEmpty);
        List<SubScribeSimple> subScribeSimples = subscribeContractMapper.selectByOpenid(subScribeCond.getOpenid());
        Map<String, Integer> collections = new HashMap<>();
        if (subScribeSimples != null && subScribeSimples.size() > 0) {
            subScribeSimples.forEach(sb -> {
                collections.put(sb.getFfid(), sb.getContractId());
            });
            List<Flight> sbuScribeList = flightMapper.selectByFfids(new ArrayList<>(collections.keySet()));
            List<FltVo> fltVos = new ArrayList<>();
            for (Flight flight : sbuScribeList) {
                FltVo fltVo = new FltVo();
                processData(flight, fltVo);
                fltVo.setSubScribeId(collections.get(flight.getUnitUfi()));
                fltVos.add(fltVo);
            }
            return new CommonResponse(fltVos);
        }else
            return new CommonResponse();
    }

    @Override
    public CommonResponse cancelSubScribeFlt(SubScribeCond subScribeCond) {
        SubscribeContract subscribeContract = subscribeContractMapper.selectOne(new QueryWrapper<SubscribeContract>().eq("contract_id", subScribeCond.getContractId()));
        if ("Y".equals(subscribeContract.getSubscribeStatus())) {
            subscribeContract.setSubscribeStatus("N");
            subscribeContract.setUpdateTime(LocalDateTime.now());
            int row = subscribeContractMapper.update(subscribeContract, new QueryWrapper<SubscribeContract>().eq("contract_id", subscribeContract.getContractId()));
            return new CommonResponse(row == 1 ? true : false);
        } else
            return new CommonResponse(ResponseStatusEnum.RepeatUnSubcribe);
    }

    void processData(Flight flight, FltVo fltVo) {
        fltVo.setSourceId(flight.getSourceId());
        /**
         * 因gufi暂时无值，故用UnitUfi站位用来查询航班详情
         */
        fltVo.setFfid(flight.getUnitUfi());
        fltVo.setFltNo(flight.getFlightNumberIata());
        fltVo.setIata(flight.getAirlineIata());
        fltVo.setFltDate(DateUtils.formatLocalDate(flight.getFlightDate()));
        fltVo.setAord(flight.getDepOrArr());
        fltVo.setDepAirportCode(flight.getDepAp());
        fltVo.setShareFltNo(flight.getCodeShareFlightList());
        fltVo.setDepSdt(DateUtils.formatLocalDateTime(flight.getScheduleTimeTakeOff()));
        fltVo.setDepEst(flight.getEstimateTakeOffTime() == null ? null : DateUtils.formatLocalDateTime(flight.getEstimateTakeOffTime()));
        fltVo.setDepAct(flight.getAtot() == null ? null : DateUtils.formatLocalDateTime(flight.getAtot()));
        fltVo.setArrAirportCode(flight.getArrAp());
        fltVo.setArrSdt(DateUtils.formatLocalDateTime(flight.getScheduleTimeLanding()));
        fltVo.setArrEst(flight.getEldt() == null ? null : DateUtils.formatLocalDateTime(flight.getEldt()));
        fltVo.setArrAct(flight.getAldt() == null ? null : DateUtils.formatLocalDateTime(flight.getAldt()));


//         航班状态加工处理
        String status = null;
        if ("A".equals(flight.getDepOrArr())) {
            status = FltStatusEnum.CNCL.getCode().equals(flight.getCahFlightStatus()) == true ? FltStatusEnum.CNCL.getDesc_cn()
                    : flight.getAldt() != null ? FltStatusEnum.ARRIVE.getDesc_cn()
                    : FltStatusEnum.DIVAL.getCode().equals(flight.getCahFlightStatus()) == true ? FltStatusEnum.DIVAL.getDesc_cn()
                    : FltStatusEnum.DELAY.getCode().equals(flight.getCahFlightStatus()) == true ?
                    FltStatusEnum.DELAY.getDesc_cn() : flight.getPreAtot() != null ?
                    FltStatusEnum.EXPECTED.getDesc_cn() : FltStatusEnum.PLAN.getDesc_cn();
        } else {
            status = FltStatusEnum.CNCL.getCode().equals(flight.getCahFlightStatus()) == true ? FltStatusEnum.CNCL.getDesc_cn()
                    : flight.getAtot() != null ? FltStatusEnum.TAKEOFF.getDesc_cn()
                    : flight.getAebt() != null ? FltStatusEnum.GATE_CLOSE.getDesc_cn()
                    : flight.getFinalCallTime() != null ? FltStatusEnum.LAST_CALL.getDesc_cn()
                    : flight.getAsbt() != null ? FltStatusEnum.BOARDING.getDesc_cn()
                    : FltStatusEnum.DELAY.getCode().equals(flight.getCahFlightStatus()) == true ?
                    FltStatusEnum.DELAY.getDesc_cn() : FltStatusEnum.PLAN.getDesc_cn();
        }
        fltVo.setFltStatus(status);

        /**
         * 出发、到达、经停机场名称加工处理
         */
        String[] split = flight.getMissionFlyinfo().split("-");
        StringBuilder routeName = new StringBuilder();
        BasAirport basAirport = null;
        if (split != null && split.length > 0) {
            for (int i = 0; i < split.length; i++) {
                //从缓存中获取，若获取不到，则查询数据库并写入缓存
                String basAirportStr = redisUtils.hget(RedisKeyConstant.Airport.BASE_AIRPORT_HASH, split[i]);
                if (basAirportStr != null) {
                    basAirport = JSONObject.parseObject(JSON.parse(basAirportStr).toString(), BasAirport.class);
                } else {
                    basAirport = basAirportMapper.selectOne(new QueryWrapper<BasAirport>().eq("airport_iata", split[i]));
                    if (basAirport != null) {
                        redisUtils.hset(RedisKeyConstant.Airport.BASE_AIRPORT_HASH, split[i], JSONObject.toJSONString(basAirport));
                    }
                }
                if (i == 0) {
                    fltVo.setDepAirportNameEn(basAirport.getAirportNameEng());
                    fltVo.setDepAirportNameCn(basAirport.getAirportChnBrief());
                } else if (i == split.length - 1) {
                    fltVo.setArrAirportNameCn(basAirport.getAirportChnBrief());
                    fltVo.setArrAirportNameEn(basAirport.getAirportNameEng());
                }
                routeName.append("-").append(basAirport.getAirportChnBrief());
            }
        }
        fltVo.setRouteName(routeName.toString().substring(1));

    }

    public static void main(String[] args) {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        map.put("1008941640", 1);
        map.put("1008941964", 2);
    }
}
