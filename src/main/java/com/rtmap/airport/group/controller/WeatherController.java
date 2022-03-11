package com.rtmap.airport.group.controller;


import com.rtmap.airport.group.result.CommonResponse;
import com.rtmap.airport.group.service.IWeatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shan
 * @since 2022-03-07
 */
@Api(tags = "天气")
@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Resource
    private IWeatherService weatherService;

    @ApiOperation("根据机场三字码查询天气")
    @GetMapping("getWeather")
    public CommonResponse getWeather(@RequestParam("airportCode") String airportCode){

        return weatherService.getWeather(airportCode);
    }
}

