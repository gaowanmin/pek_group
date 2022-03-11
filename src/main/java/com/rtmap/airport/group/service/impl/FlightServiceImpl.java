package com.rtmap.airport.group.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rtmap.airport.group.conf.RedisUtils;
import com.rtmap.airport.group.entity.*;
import com.rtmap.airport.group.entity.cond.FltCond;
import com.rtmap.airport.group.mapper.*;
import com.rtmap.airport.group.vo.FltVo;
import com.rtmap.airport.group.enums.FltStatusEnum;
import com.rtmap.airport.group.enums.RedisKeyConstant;
import com.rtmap.airport.group.result.CommonResponse;
import com.rtmap.airport.group.result.ResponseStatusEnum;
import com.rtmap.airport.group.service.IFlightService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rtmap.airport.group.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author shan
 * @since 2022-02-28
 */
@Service
public class FlightServiceImpl extends ServiceImpl<FlightMapper, Flight> implements IFlightService {

    @Resource
    private FlightMapper flightMapper;

    @Resource
    private BasAirportMapper basAirportMapper;

    @Resource
    private BasAirlineMapper basAirlineMapper;

    @Resource
    private WeatherMapper weatherMapper;

    @Resource
    private SubscribeContractMapper subscribeContractMapper;

    @Resource
    private RedisUtils redisUtils;


    @Override
    public CommonResponse getByFltNo(FltCond fltCond) {
        QueryWrapper<Flight> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isEmpty(fltCond.getCurrAirportCode()))
            return new CommonResponse(ResponseStatusEnum.CurrentAirportNotEmpty);
        queryWrapper.eq("source_id", fltCond.getCurrAirportCode());
        if (StringUtils.isEmpty(fltCond.getQueryDate()))
            return new CommonResponse(ResponseStatusEnum.FltDateNotEmpty);
        queryWrapper.eq("flight_date", fltCond.getQueryDate());
        if (StringUtils.isEmpty(fltCond.getFltNo()))
            return new CommonResponse(ResponseStatusEnum.FltNoNotEmpty);
        /**匹配航班号和共享航班号*/
        queryWrapper.and(w -> w.like("flight_number_iata", fltCond.getFltNo()).or().like("code_share_flight_list", fltCond.getFltNo()));
        if (!StringUtils.isEmpty(fltCond.getAord()))
            queryWrapper.eq("dep_or_arr", fltCond.getAord());
        queryWrapper.orderByAsc("schedule_time_take_off");
        List<Flight> flights = flightMapper.selectList(queryWrapper);
        List<FltVo> fltVos = new ArrayList<>();
        if (flights != null && flights.size() > 0) {
            for (Flight flight : flights) {
                FltVo fltVo = new FltVo();
                processData(flight, fltVo);
                fltVos.add(fltVo);
            }
        }
        return new CommonResponse(fltVos);
    }

    @Override
    public CommonResponse getByPlaceCond(FltCond fltCond, int page, int pageSize) {
        Page<Flight> fltPage = new Page<>(page, pageSize);
        Page<FltVo> fltVoPage = new Page<>(page, pageSize);
        QueryWrapper<Flight> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isEmpty(fltCond.getCurrAirportCode()))
            return new CommonResponse(ResponseStatusEnum.CurrentAirportNotEmpty);
        queryWrapper.eq("source_id", fltCond.getCurrAirportCode());
        if (StringUtils.isEmpty(fltCond.getQueryDate()))
            return new CommonResponse(ResponseStatusEnum.FltDateNotEmpty);
        queryWrapper.eq("flight_date", fltCond.getQueryDate());
        if (StringUtils.isEmpty(fltCond.getAirportCode()))
            return new CommonResponse(ResponseStatusEnum.AirportCodeNotEmpty);
        if (StringUtils.isEmpty(fltCond.getAord()))
            return new CommonResponse(ResponseStatusEnum.AordNotEmpty);
        if ("A".equals(fltCond.getAord())) {
            queryWrapper.eq("dep_ap", fltCond.getAirportCode());
            queryWrapper.orderByAsc("schedule_time_landing");
        } else {
            queryWrapper.eq("arr_ap", fltCond.getAirportCode());
            queryWrapper.orderByAsc("schedule_time_take_off");
        }
        Page<Flight> flightPage = flightMapper.selectPage(fltPage, queryWrapper);
        List<FltVo> fltVos = new ArrayList<>();
        flightPage.getRecords().forEach(flight -> {
            FltVo fltVo = new FltVo();
            processData(flight, fltVo);
            fltVos.add(fltVo);
        });
        fltVoPage.setRecords(fltVos);
        fltVoPage.setTotal(flightPage.getTotal());
        return new CommonResponse(fltVoPage);
    }

    @Override
    public CommonResponse getDetailByFfid(FltCond fltCond) {
        if (StringUtils.isEmpty(fltCond.getCurrAirportCode()))
            return new CommonResponse(ResponseStatusEnum.CurrentAirportNotEmpty);
        if (StringUtils.isEmpty(fltCond.getFfid()))
            return new CommonResponse(ResponseStatusEnum.FfidNotEmpty);
        if (StringUtils.isEmpty(fltCond.getOpenid()))
            return new CommonResponse(ResponseStatusEnum.OpenidNotEmpty);
        Flight flight = flightMapper.selectOne(new QueryWrapper<Flight>().eq("source_id", fltCond.getCurrAirportCode()).eq("unit_ufi", fltCond.getFfid()));
        FltVo fltVo = new FltVo();
        /** 列表属性加工*/
        processData(flight, fltVo);
        /** 详情页特殊属性加工*/
        processDetailData(flight, fltVo, fltCond.getOpenid());
        return new CommonResponse(fltVo);
    }

    void processDetailData(Flight flight, FltVo fltVo, String openid) {
        fltVo.setSourceId(flight.getSourceId());
        /**
         * 因gufi暂时无值，故用UnitUfi站位用来查询航班详情
         */
        fltVo.setTask(flight.getMissionProperty());
        fltVo.setDomint(flight.getFlightSegmentDori());
        fltVo.setRegNumber(flight.getRegNumber());
        fltVo.setAirType(flight.getAircraftType());
        fltVo.setDepTerminal(flight.getDepTerminal());
        fltVo.setArrTerminal(flight.getArrTerminal());
        fltVo.setArrPark(flight.getArrStand());

        fltVo.setCnlReason(flight.getCnlReasonChn());
        fltVo.setRoute(flight.getMissionFlyinfo());
        fltVo.setCounter(flight.getCheckInCounter());
        fltVo.setGate(flight.getDepGate());
        fltVo.setBelt(flight.getExpectedArrivalLuggageTurntable());
        fltVo.setBoardingTime(flight.getAsbt() == null ? null : DateUtils.formatLocalDateTime(flight.getAsbt()));
        fltVo.setUrageBoardingTime(flight.getFinalCallTime() == null ? null : DateUtils.formatLocalDateTime(flight.getFinalCallTime()));
        fltVo.setEndBoardingTime(flight.getAebt() == null ? null : DateUtils.formatLocalDateTime(flight.getAebt()));
        fltVo.setFirstBaggageTime(flight.getBaggageArrTime() == null ? null : DateUtils.formatLocalDateTime(flight.getBaggageArrTime()));
        fltVo.setLastBaggageTime(flight.getArrLastLuggageTime() == null ? null : DateUtils.formatLocalDateTime(flight.getArrLastLuggageTime()));
        fltVo.setPreActTime(flight.getPreAtot() == null ? null : DateUtils.formatLocalDateTime(flight.getPreAtot()));
        fltVo.setNextActTime(flight.getNextAldt() == null ? null : DateUtils.formatLocalDateTime(flight.getNextAldt()));

        /**
         * 航司名称加工处理
         */
        String cacheBasAirline = redisUtils.hget(RedisKeyConstant.Airport.BASE_AIRLINE_HASH, fltVo.getIata());
        if (StringUtils.isEmpty(cacheBasAirline)) {
            //从缓存中获取航司信息，如果获取不到查询DB,写入缓存
            BasAirline basAirline = basAirlineMapper.selectOne(new QueryWrapper<BasAirline>().eq("airline_iata", fltVo.getIata()));
            if (basAirline != null) {
                redisUtils.hset(RedisKeyConstant.Airport.BASE_AIRLINE_HASH, fltVo.getIata(), JSONObject.toJSONString(basAirline));
                fltVo.setAirlinName(basAirline.getAirlineChnBrief());
            }
        } else {
            BasAirline basAirline = JSONObject.parseObject(JSON.parse(cacheBasAirline).toString(), BasAirline.class);
            fltVo.setAirlinName(basAirline.getAirlineChnBrief());
        }

         /**
         * 天气加工处理
         */
        if("A".equals(flight.getDepOrArr())){
            Weather w = weatherMapper.selectOne(new QueryWrapper<Weather>().eq("airport_code", flight.getArrAp()));
            if(w != null){
                AirportWeather airportWeather = new AirportWeather();
                airportWeather.setAirportCode(w.getAirportCode());
                airportWeather.setAriportNameAbbr(w.getAriportNameAbbr());
                airportWeather.setPm(w.getPm());
                airportWeather.setTemperature(w.getTemperature());
                airportWeather.setWeather(w.getWeather());
                fltVo.setArrAirportWeather(airportWeather);
            }
        }else{
            Weather w = weatherMapper.selectOne(new QueryWrapper<Weather>().eq("airport_code", flight.getDepAp()));
            if(w != null){
                AirportWeather airportWeather = new AirportWeather();
                airportWeather.setAirportCode(w.getAirportCode());
                airportWeather.setAriportNameAbbr(w.getAriportNameAbbr());
                airportWeather.setPm(w.getPm());
                airportWeather.setTemperature(w.getTemperature());
                airportWeather.setWeather(w.getWeather());
                fltVo.setDepAirportWeather(airportWeather);
            }
        }

        /**
         * 判断用户是否订阅该航班
         */
        List<Integer> subScribeSimples= subscribeContractMapper.selectByParams(openid,flight.getUnitUfi());
        if(subScribeSimples != null && subScribeSimples.size() > 0){
            fltVo.setSubScribeId(subScribeSimples.get(0));
        }else
            fltVo.setSubScribeId(0);
    }

    void processData(Flight flight, FltVo fltVo) {
        fltVo.setSourceId(flight.getSourceId());
        /**
         * 因gufi暂时无值，故用UnitUfi站位用来查询航班详情
         */
        fltVo.setFfid(flight.getUnitUfi());
        fltVo.setIata(flight.getAirlineIata());
        fltVo.setFltNo(flight.getFlightNumberIata());
        fltVo.setFltDate(DateUtils.formatLocalDate(flight.getFlightDate()));
        fltVo.setAord(flight.getDepOrArr());
        fltVo.setShareFltNo(flight.getCodeShareFlightList());
        fltVo.setDepAirportCode(flight.getDepAp());
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
        StringBuilder passRouteName = new StringBuilder();
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
                    fltVo.setDepAirportNameCn(basAirport.getAirportChnBrief());
                    fltVo.setDepAirportNameEn(basAirport.getAirportNameEng());
                } else if (i == split.length - 1) {
                    fltVo.setArrAirportNameCn(basAirport.getAirportChnBrief());
                    fltVo.setArrAirportNameEn(basAirport.getAirportNameEng());
                } else {
                    passRouteName.append("-").append(basAirport.getAirportChnBrief());
                }
                routeName.append("-").append(basAirport.getAirportChnBrief());
            }
        }
        fltVo.setRouteName(routeName.toString().substring(1));
        fltVo.setPassRoute(StringUtils.isEmpty(passRouteName)? null : passRouteName.toString().substring(1));

    }


}
