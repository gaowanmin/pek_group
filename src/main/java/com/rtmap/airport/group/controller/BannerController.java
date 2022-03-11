package com.rtmap.airport.group.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rtmap.airport.group.entity.Banner;
import com.rtmap.airport.group.enums.BannerStatusType;
import com.rtmap.airport.group.result.CommonResponse;
import com.rtmap.airport.group.service.IBannerService;
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
 * banner信息表 前端控制器
 * </p>
 *
 * @author shan
 * @since 2022-02-18
 */
@Api(tags = "banner信息")
@RestController
@RequestMapping("/banner")
public class BannerController {
    @Resource
    private IBannerService bannerService;

    @ApiOperation("获取banner列表")
    @GetMapping("/list")
    public CommonResponse list(@RequestParam String airportCode) {
        QueryWrapper<Banner> wrapper = new QueryWrapper<>();
        wrapper.eq("airport_code", airportCode.toUpperCase());
        wrapper.eq("status", BannerStatusType.PUBLISH.getStatus());
        wrapper.le("start_time", DateUtils.formatDateYMDHMS(new Date()));
        wrapper.ge("end_time", DateUtils.formatDateYMDHMS(new Date()));
        wrapper.orderByAsc("sort");
        List<Banner> list = bannerService.list(wrapper);
        return new CommonResponse(list);
    }

    @ApiOperation("获取banner详情")
    @GetMapping("/detail")
    public CommonResponse detail(@RequestParam Integer bannerId) {
        return new CommonResponse(bannerService.getById(bannerId));
    }

}

