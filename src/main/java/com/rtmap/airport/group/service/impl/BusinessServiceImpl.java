package com.rtmap.airport.group.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rtmap.airport.group.entity.Business;
import com.rtmap.airport.group.mapper.BusinessMapper;
import com.rtmap.airport.group.result.CommonResponse;
import com.rtmap.airport.group.service.IBusinessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rtmap.airport.group.vo.BusinessSearchVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 商业店铺 服务实现类
 * </p>
 *
 * @author shan
 * @since 2022-02-24
 */
@Service
public class BusinessServiceImpl extends ServiceImpl<BusinessMapper, Business> implements IBusinessService {

    @Resource
    private BusinessMapper businessMapper;

    @Override
    public CommonResponse list(BusinessSearchVo vo) {
        Page<Business> businessPage = new Page<>(vo.getPage(), vo.getPageSize());
        QueryWrapper<Business> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(vo.getAirportCode()), "airport_code", vo.getAirportCode().toUpperCase())
                .eq(StringUtils.isNotBlank(vo.getTerminal()), "terminal", vo.getTerminal())
                .eq(StringUtils.isNotBlank(vo.getPrimaryCategroy()), "primary_categroy", vo.getPrimaryCategroy())
                .like(StringUtils.isNotBlank(vo.getSecondCategroy()), "second_categroy", vo.getSecondCategroy())
                .eq(StringUtils.isNotBlank(vo.getFloor()), "floor", vo.getFloor())
                .eq(StringUtils.isNotBlank(vo.getArea()), "area", vo.getArea())
                .like(StringUtils.isNotBlank(vo.getKeyWords()), "key_words", vo.getKeyWords())
                .eq(StringUtils.isNotBlank(vo.getOthersService()), "others_service", vo.getOthersService())
                .eq(vo.getFlagSuggest() != null, "flag_suggest", vo.getFlagSuggest());
        IPage<Business> iPage = businessMapper.selectPage(businessPage, queryWrapper);
        return new CommonResponse(iPage);
    }
}
