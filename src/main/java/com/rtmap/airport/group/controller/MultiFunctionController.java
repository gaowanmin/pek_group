package com.rtmap.airport.group.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rtmap.airport.group.entity.AirportGroupMultiFunction;
import com.rtmap.airport.group.entity.AirportGroupMultiFunctionDetail;
import com.rtmap.airport.group.entity.AirportGroupType;
import com.rtmap.airport.group.enums.BrandStatusType;
import com.rtmap.airport.group.result.CommonResponse;
import com.rtmap.airport.group.result.ResponseStatusEnum;
import com.rtmap.airport.group.service.IAirportGroupMultiFunctionDetailService;
import com.rtmap.airport.group.service.IAirportGroupMultiFunctionService;
import com.rtmap.airport.group.service.IAirportGroupTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "多功能列表信息")
@RestController
@RequestMapping("/multiFunction")
public class MultiFunctionController {

    @Resource
    private IAirportGroupMultiFunctionService multiFunctionService;

    @Resource
    private IAirportGroupTypeService groupTypeService;

    @Resource
    private IAirportGroupMultiFunctionDetailService detailService;

    @ApiOperation(value = "功能列表查询 0、特色服务 1、全部服务 2、出发/到达/中转 3、未关注航班-服务入口" +
                                    " 4、关注出发航班-服务入口 5、关注到达航班-服务入口 6、关注出发航班全部服务 7、关注到达航班全部服务")
    @GetMapping("/list")
    public CommonResponse list(@RequestParam(required = false, defaultValue = "PEK") String airportCode,
                               @RequestParam Integer typeCode) {
        QueryWrapper<AirportGroupType> typeWrapper = new QueryWrapper<>();
        typeWrapper.eq("status", BrandStatusType.OPEN.getStatus())
        .eq("airport_code", airportCode.toUpperCase()).orderByAsc("sort_id");

        QueryWrapper<AirportGroupMultiFunction> wrapper = new QueryWrapper<>();
        wrapper.eq("status", BrandStatusType.OPEN.getStatus())
        .eq("airport_code", airportCode.toUpperCase()).orderByAsc("sort_id");
        List<AirportGroupType> typeList = null;

        if (0 == typeCode) {
            wrapper.eq("is_characteristic", "T");
            return new CommonResponse(multiFunctionService.list(wrapper));
        }
        typeWrapper.eq("type_code", typeCode);//1、全部服务 2、出行决策
        typeList = groupTypeService.list(typeWrapper);
        if (CollectionUtils.isEmpty(typeList)) {
            return new CommonResponse(ResponseStatusEnum.DATABASE_DONT_NO);
        }
        typeList.forEach(type -> {
            wrapper.clear();//避免多追加相同查询条件
            wrapper.eq("status", BrandStatusType.OPEN.getStatus());
            wrapper.eq("airport_code", airportCode.toUpperCase());
            wrapper.orderByAsc("sort_id");
            wrapper.eq("type_id", type.getId());
            List<AirportGroupMultiFunction> muList = multiFunctionService.list(wrapper);
            type.setMultiFunctions(muList);
        });
        return new CommonResponse(typeList);
    }

    @ApiOperation(value = "根据二级子项ID获取详情，含富文本")
    @GetMapping("/detail")
    public CommonResponse detail(@RequestParam(required = false, defaultValue = "PEK") String airportCode,
                                 @RequestParam Integer functionId) {
        Map<String, Object> resultMap = new HashMap<>(3);
        QueryWrapper<AirportGroupMultiFunction> multiWrapper = new QueryWrapper<>();
        multiWrapper.eq("id", functionId);
        AirportGroupMultiFunction multi = multiFunctionService.getOne(multiWrapper);
        QueryWrapper<AirportGroupMultiFunctionDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("status", BrandStatusType.OPEN.getStatus())
        .eq("airport_code", airportCode.toUpperCase()).eq("function_id", functionId).orderByAsc("sort_id");
        List<AirportGroupMultiFunctionDetail> list = detailService.list(wrapper);
        resultMap.put("details", list);
        resultMap.put("function_name", multi == null ? "" : multi.getTitle());
        resultMap.put("function_imgDetail", multi == null ? "" : multi.getImgDetail());
        return new CommonResponse(resultMap);
    }



}







