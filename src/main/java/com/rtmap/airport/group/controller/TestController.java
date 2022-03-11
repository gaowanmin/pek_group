package com.rtmap.airport.group.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rtmap.airport.group.aop.LogAnnotation;
import com.rtmap.airport.group.entity.User;
import com.rtmap.airport.group.mapper.UserMapper;
import com.rtmap.airport.group.result.CommonResponse;
import com.rtmap.airport.group.service.IUserService;
import com.rtmap.airport.group.util.BindingResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author: shan
 * @date: 2022/02/16
 */
@Api(tags = "测试类")
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private IUserService iUserService;
    @Resource
    private UserMapper userMapper;

    @LogAnnotation("获取用户信息")
    @ApiOperation("获取用户信息")
    @GetMapping("/getUser")
    public CommonResponse getUser(@RequestParam Integer id) {
        return new CommonResponse(iUserService.getById(id));
    }

    @ApiOperation("获取所有用户信息")
    @GetMapping("/getAllUsers")
    public CommonResponse getAllUsers(@RequestParam Integer page, @RequestParam Integer size) {
        Page<User> pageBean = new Page<>(page, size);
        IPage<User> selectPage = userMapper.selectPage(pageBean, null);

        return new CommonResponse(selectPage);
    }

    @ApiOperation("根据条件查询用户信息")
    @GetMapping("/getAllUsersByParam")
    public CommonResponse getAllUsersByParam(@RequestParam String sex) {
        //自定义
        IPage<User> selectPage = userMapper.selectPageBySex(new Page<>(), sex);
        //也可以这样
        //QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        //userQueryWrapper.eq("sex", "男");
        //userMapper.selectList(userQueryWrapper);
        return new CommonResponse(selectPage);
    }

    @LogAnnotation("更新用户信息")
    @ApiOperation("更新用户信息")
    @PostMapping("/updateUser")
    public CommonResponse updateUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        CommonResponse result = BindingResultUtil.validate(bindingResult);
        if (result != null) {
            return result;
        }
        return new CommonResponse(iUserService.updateById(user));
    }
}
