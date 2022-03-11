package com.rtmap.airport.group.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rtmap.airport.group.entity.AirportGroup;
import com.rtmap.airport.group.entity.AirportGroupTerminal;
import com.rtmap.airport.group.mapper.AirportGroupMapper;
import com.rtmap.airport.group.mapper.AirportGroupTerminalMapper;
import com.rtmap.airport.group.result.CommonResponse;
import com.rtmap.airport.group.service.IAirportGroupService;
import com.rtmap.airport.group.util.Distance;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AirportGroupServiceImpl extends ServiceImpl<AirportGroupMapper, AirportGroup> implements IAirportGroupService {

    @Resource
    private AirportGroupMapper airportGroupMapper;
    @Resource
    private AirportGroupTerminalMapper airportGroupTerminalMapper;

    @Override
    public CommonResponse list(Double lat, Double lng, int page, int size) {
        List<AirportGroup> list = new ArrayList<>();
        QueryWrapper<AirportGroup> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1);
        Page<AirportGroup> pageBean = new Page<>(page, size);
        List<AirportGroup> airportGroupsList = airportGroupMapper.selectPage(pageBean, wrapper).getRecords();
        if (!CollectionUtils.isEmpty(airportGroupsList)) {
            for (AirportGroup airportGroup : airportGroupsList) {
                if (!StringUtils.isEmpty(lat) && !StringUtils.isEmpty(lng)) {
                    Double dist = Distance.GetDistance(lng, lat, airportGroup.getLng(), airportGroup.getLat());
                    airportGroup.setDistance(dist);
                } else {
                    //默认推荐首都机场
                    if ("PEK".equals(airportGroup.getAirportCode())) {
                        airportGroup.setDistance(0.0);
                    }
                }
                list.add(airportGroup);
            }
        }
        Collections.sort(list);
        return new CommonResponse(list);
    }

    @Override
    public CommonResponse detail(String airportCode) {
        QueryWrapper<AirportGroup> wrapper = new QueryWrapper<>();
        wrapper.eq("airport_code", airportCode.toUpperCase());
        AirportGroup store = airportGroupMapper.selectOne(wrapper);
        List<AirportGroupTerminal> airportGroupTerminals = airportGroupTerminalMapper.selectList(new QueryWrapper<AirportGroupTerminal>().select("terminal", "floor").eq("airport_code", airportCode).eq("status", 1));
        if (!CollectionUtils.isEmpty(airportGroupTerminals)) {
            Map<String, List<AirportGroupTerminal>> map = airportGroupTerminals.stream().collect(Collectors.groupingBy(AirportGroupTerminal::getTerminal));
            store.setAirportGroupTerminal(map);
        }
        return new CommonResponse(store);
    }


}
