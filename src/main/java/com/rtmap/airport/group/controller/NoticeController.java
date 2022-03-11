package com.rtmap.airport.group.controller;


import com.rtmap.airport.group.result.CommonResponse;
import com.rtmap.airport.group.service.INoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author shan
 * @since 2022-02-22
 */
@Api(tags = "机场消息")
@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Resource
    private INoticeService iNoticeService;

    @ApiOperation("获取消息列表")
    @GetMapping("/list")
    public CommonResponse list(@RequestParam String airportCode, @RequestParam(required = false, defaultValue = "0") Integer page, @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return iNoticeService.list(airportCode, page, pageSize);
    }

}

