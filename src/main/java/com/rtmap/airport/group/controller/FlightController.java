package com.rtmap.airport.group.controller;


import com.rtmap.airport.group.entity.cond.FltCond;
import com.rtmap.airport.group.result.CommonResponse;
import com.rtmap.airport.group.service.IBasAirportService;
import com.rtmap.airport.group.service.IFlightService;
import com.rtmap.airport.group.service.IHotAirportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shan
 * @since 2022-02-28
 */
@Api(tags = "航班")
@RestController
@RequestMapping("/flight")
public class FlightController {

    @Resource
    private IFlightService flightService;

    @Resource
    private IBasAirportService airportService;

    @Resource
    private IHotAirportService hotAirportService;

    //=============================================================================
    //===============================基础机场查询==============================
    //=============================================================================

    @GetMapping(value = "basAirport")
    @ApiOperation("基础机场查询")
    public CommonResponse basAirport() {
        return airportService.getAirport();
    }

    @GetMapping(value = "getHotAirport")
    @ApiOperation("热门机场查询")
    public CommonResponse getHotAirport(@RequestParam String airportCode) {

        return hotAirportService.getHotAirport(airportCode);
    }



    @PostMapping(value = "getByFltNo")
    @ApiOperation("根据航班号查询航班")
    public CommonResponse getByFltNo(@RequestBody FltCond fltCond) {
        return flightService.getByFltNo(fltCond);
    }


    @PostMapping(value = "getByPlaceCond")
    @ApiOperation("根据起降地查询航班")
    public CommonResponse getByPlaceCond(@RequestBody FltCond fltCond, @RequestParam(required = false, defaultValue = "1") int page, @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return flightService.getByPlaceCond(fltCond,page,pageSize);
    }


    @PostMapping(value = "getDetailByFfid")
    @ApiOperation("获取航班详情")
    public CommonResponse getDetailByFfid(@RequestBody FltCond fltCond) {
        return flightService.getDetailByFfid(fltCond);
    }
}

