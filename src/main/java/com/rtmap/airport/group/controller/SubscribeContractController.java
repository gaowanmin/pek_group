package com.rtmap.airport.group.controller;


import com.rtmap.airport.group.entity.SubscribeContract;
import com.rtmap.airport.group.entity.cond.SubScribeCond;
import com.rtmap.airport.group.result.CommonResponse;
import com.rtmap.airport.group.service.ISubscribeContractService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订阅契约表 前端控制器
 * </p>
 *
 * @author shan
 * @since 2022-03-03
 */
@Api(tags = "航班订阅")
@RestController
@RequestMapping("/subscribeContract")
public class SubscribeContractController {

    @Autowired
    private ISubscribeContractService subscribeContractService;

    @ApiOperation("获取当前用户订阅航班唯一标识集合")
    @PostMapping(value = "getFltBySubScribe")
    public CommonResponse getFltBySubScribe(@RequestBody SubScribeCond subScribeCond){

        return subscribeContractService.getFltBySubScribe(subScribeCond);
    }

    @ApiOperation("订阅航班")
    @PostMapping(value = "subScribeFlt")
    public CommonResponse subScribeFlt(@RequestBody SubscribeContract subscribeContract){
        return subscribeContractService.subScribeFlt(subscribeContract);
    }

    @ApiOperation("取消订阅航班")
    @PostMapping(value = "cancelSubScribeFlt")
    public CommonResponse subScribeFlt(@RequestBody SubScribeCond subScribeCond){
        return subscribeContractService.cancelSubScribeFlt(subScribeCond);
    }
}

