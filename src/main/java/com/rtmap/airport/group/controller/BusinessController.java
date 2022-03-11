package com.rtmap.airport.group.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.rtmap.airport.group.entity.AirportGroupTerminal;
import com.rtmap.airport.group.entity.Category;
import com.rtmap.airport.group.result.CommonResponse;
import com.rtmap.airport.group.service.IBusinessService;
import com.rtmap.airport.group.service.ICategoryService;
import com.rtmap.airport.group.util.BindingResultUtil;
import com.rtmap.airport.group.vo.BusinessSearchVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 商业店铺 前端控制器
 * </p>
 *
 * @author shan
 * @since 2022-02-24
 */
@Api(tags = "商业相关")
@RestController
@RequestMapping("/business")
public class BusinessController {

    @Resource
    private IBusinessService iBusinessService;
    @Resource
    private ICategoryService iCategoryService;

    @ApiOperation(value = "商业类型信息")
    @PostMapping("/category/list")
    public CommonResponse categoryList() {
        QueryWrapper<Category> wrapper = new QueryWrapper();
        wrapper.eq("status", 1);
        List<Category> list = iCategoryService.list(wrapper);
        if (CollectionUtils.isNotEmpty(list)) {
            Map<String, List<Category>> map = list.stream().collect(Collectors.groupingBy(Category::getPrimaryCategory));
            return new CommonResponse(map);
        }
        return new CommonResponse();
    }


    @ApiOperation(value = "根据条件查询商业信息")
    @PostMapping("/list")
    public CommonResponse list(@Valid @RequestBody BusinessSearchVo vo, BindingResult bindingResult) {
        CommonResponse result = BindingResultUtil.validate(bindingResult);
        if (result != null) {
            return result;
        }
        return iBusinessService.list(vo);
    }

    @ApiOperation(value = "店铺详情")
    @PostMapping("/detail")
    public CommonResponse detail(@RequestParam Integer id) {
        return new CommonResponse(iBusinessService.getById(id));
    }
}

