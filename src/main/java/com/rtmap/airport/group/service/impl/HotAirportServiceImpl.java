package com.rtmap.airport.group.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rtmap.airport.group.conf.RedisUtils;
import com.rtmap.airport.group.entity.HotAirport;
import com.rtmap.airport.group.enums.DomintConstant;
import com.rtmap.airport.group.enums.RedisKeyConstant;
import com.rtmap.airport.group.mapper.HotAirportMapper;
import com.rtmap.airport.group.result.CommonResponse;
import com.rtmap.airport.group.service.IHotAirportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 中文全称 服务实现类
 * </p>
 *
 * @author shan
 * @since 2022-02-28
 */
@Service
public class HotAirportServiceImpl extends ServiceImpl<HotAirportMapper, HotAirport> implements IHotAirportService {

    @Autowired
    private HotAirportMapper hotAirportMapper;

    @Resource
    private RedisUtils redisUtils;

    @Override
    public CommonResponse getHotAirport(String airportCode) {
        String hotAirports = redisUtils.get(RedisKeyConstant.Airport.HOT_AIRPORT+"_"+airportCode);
        if (hotAirports != null) {
            return new CommonResponse(hotAirports);
        }
        Map<String, Object> result = new HashMap<>();
        QueryWrapper<HotAirport> wrapper = new QueryWrapper<>();
        wrapper.eq("curr_airport_code",airportCode);
        wrapper.eq("domint",DomintConstant.D);
        List<HotAirport> demoHotAirports = hotAirportMapper.selectList(wrapper);
        wrapper.eq("domint",DomintConstant.I);
        List<HotAirport> initHotAirports = hotAirportMapper.selectList(wrapper);
        if (initHotAirports != null)
            result.put("initHotAirports", initHotAirports);
        if (demoHotAirports != null)
            result.put("demoHotAirports", demoHotAirports);
        if (!result.isEmpty()) {
            redisUtils.setWithTimeOut(RedisKeyConstant.Airport.HOT_AIRPORT+"_"+airportCode, JSONObject.toJSONString(result),60 * 60 * 24);
        }
        return new CommonResponse(JSONObject.toJSONString(result));
    }
}
