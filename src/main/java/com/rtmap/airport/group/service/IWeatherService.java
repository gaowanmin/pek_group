package com.rtmap.airport.group.service;

import com.rtmap.airport.group.entity.Weather;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rtmap.airport.group.result.CommonResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shan
 * @since 2022-03-07
 */
public interface IWeatherService extends IService<Weather> {

    CommonResponse getWeather(String airportCode);

}
