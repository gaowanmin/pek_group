package com.rtmap.airport.group.service;

import com.rtmap.airport.group.entity.Business;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rtmap.airport.group.result.CommonResponse;
import com.rtmap.airport.group.vo.BusinessSearchVo;

/**
 * <p>
 * 商业店铺 服务类
 * </p>
 *
 * @author shan
 * @since 2022-02-24
 */
public interface IBusinessService extends IService<Business> {

    CommonResponse list(BusinessSearchVo vo);
}
