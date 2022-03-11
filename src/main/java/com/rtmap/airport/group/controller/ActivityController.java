package com.rtmap.airport.group.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rtmap.airport.group.entity.Activity;
import com.rtmap.airport.group.enums.ActivityStatusType;
import com.rtmap.airport.group.result.CommonResponse;
import com.rtmap.airport.group.service.IActivityService;
import com.rtmap.airport.group.util.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 活动信息表 前端控制器
 * </p>
 *
 * @author shan
 * @since 2022-03-08
 */
@Api(tags = "活动信息")
@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Resource
    private IActivityService iActivityService;

    @ApiOperation("获取活动列表")
    @GetMapping("/list")
    public CommonResponse list(@RequestParam String airportCode) {
        QueryWrapper<Activity> wrapper = new QueryWrapper<>();
        wrapper.eq("airport_code", airportCode.toUpperCase());
        wrapper.eq("status", ActivityStatusType.PUBLISH.getStatus());
        wrapper.le("start_time", DateUtils.formatDateYMDHMS(new Date()));
        wrapper.ge("end_time", DateUtils.formatDateYMDHMS(new Date()));
        wrapper.orderByAsc("sort");
        List<Activity> list = iActivityService.list(wrapper);
        return new CommonResponse(list);
    }

    @ApiOperation("获取活动详情")
    @GetMapping("/detail")
    public CommonResponse detail(@RequestParam Integer activityId) {
        return new CommonResponse(iActivityService.getById(activityId));
    }
}

