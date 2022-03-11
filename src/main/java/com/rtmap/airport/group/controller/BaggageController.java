package com.rtmap.airport.group.controller;

import com.rtmap.airport.group.result.CommonResponse;
import com.rtmap.airport.group.service.IBaggageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: gaowm
 * @date: 2022/3/7
 * @desc: 行李查询控制器
 */
@Api(tags = "行李")
@RequestMapping("baggage")
@RestController
public class BaggageController {

    @Resource
    private IBaggageService baggageService;

    @ApiOperation("根据行李编号查询行李")
    @PostMapping("queryBaggage")
    @RequestBody
    public CommonResponse queryBaggage(@RequestParam("bagNo") String bagNo){

        return baggageService.queryBaggage(bagNo);
    }
}
