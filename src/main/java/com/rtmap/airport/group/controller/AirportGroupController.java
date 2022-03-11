package com.rtmap.airport.group.controller;

import com.rtmap.airport.group.result.CommonResponse;
import com.rtmap.airport.group.service.IAirportGroupMultiFunctionDetailService;
import com.rtmap.airport.group.service.IAirportGroupMultiFunctionService;
import com.rtmap.airport.group.service.IAirportGroupService;
import com.rtmap.airport.group.service.IAirportGroupTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "集团机场信息")
@RestController
@RequestMapping("/airportGroup")
public class AirportGroupController {

    @Resource
    private IAirportGroupService airportGroupService;

    @Resource
    private IAirportGroupMultiFunctionService multiFunctionService;

    @Resource
    private IAirportGroupTypeService groupTypeService;

    @Resource
    private IAirportGroupMultiFunctionDetailService detailService;

    @ApiOperation(value = "支持用户定位，查询距离机场列表")
    @GetMapping("/list")
    public CommonResponse list(@RequestParam(required = false) Double lat,
                               @RequestParam(required = false) Double lng,
                               @RequestParam(required = false, defaultValue = "1") int page,
                               @RequestParam(required = false, defaultValue = "20") int size) {
        return airportGroupService.list(lat, lng, page, size);
    }

    @ApiOperation(value = "机场详情")
    @GetMapping("/detail")
    public CommonResponse detail(@RequestParam String airportCode) {
        return airportGroupService.detail(airportCode);
    }

}







