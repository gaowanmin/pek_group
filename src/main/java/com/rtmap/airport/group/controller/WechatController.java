package com.rtmap.airport.group.controller;

import com.rtmap.airport.group.result.CommonResponse;
import com.rtmap.airport.group.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author: shan
 * @date: 2022/02/16
 */
@Api(tags = "微信相关")
@RestController
@RequestMapping("/wechat")
public class WechatController {

    @Resource
    private IUserService iUserService;

    @ApiOperation("微信小程序登陆")
    @PostMapping("/login")
    public CommonResponse login(@RequestParam String code) {
        return iUserService.login(code);
    }

    @ApiOperation("解析微信用户信息")
    @PostMapping("/getUserInfo")
    public CommonResponse getUserInfo(@RequestParam String openId,
                                      @RequestParam String encryptedData,
                                      @RequestParam String iv) {
        return iUserService.getUserInfo(openId, encryptedData, iv);
    }
}
