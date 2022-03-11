package com.rtmap.airport.group.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.rtmap.airport.group.entity.AirportWeather;
import com.rtmap.airport.group.entity.Weather;
import com.rtmap.airport.group.mapper.WeatherMapper;
import com.rtmap.airport.group.result.CommonResponse;
import com.rtmap.airport.group.result.ResponseStatusEnum;
import com.rtmap.airport.group.service.IWeatherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author shan
 * @since 2022-03-07
 */
@Service
public class WeatherServiceImpl extends ServiceImpl<WeatherMapper, Weather> implements IWeatherService {

    @Autowired
    private WeatherMapper weatherMapper;

    @Override
    public CommonResponse getWeather(String airportCode) {
        if (StringUtils.isEmpty(airportCode))
            return new CommonResponse(ResponseStatusEnum.AirportCodeNotEmpty);
        Weather weather = weatherMapper.selectOne(new QueryWrapper<Weather>().eq("airport_code", airportCode));
        AirportWeather airportWeather = new AirportWeather();
        if (weather != null) {
            airportWeather.setAirportCode(weather.getAirportCode());
            airportWeather.setAriportNameAbbr(weather.getAriportNameAbbr());
            airportWeather.setPm(weather.getPm());
            airportWeather.setTemperature(weather.getTemperature());
            airportWeather.setWeather(weather.getWeather());
        }
        return new CommonResponse(airportWeather);
    }
}
