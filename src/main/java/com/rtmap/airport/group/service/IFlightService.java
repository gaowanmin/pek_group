package com.rtmap.airport.group.service;

import com.rtmap.airport.group.entity.Flight;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rtmap.airport.group.entity.cond.FltCond;
import com.rtmap.airport.group.result.CommonResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shan
 * @since 2022-02-28
 */
public interface IFlightService extends IService<Flight> {

    CommonResponse getByFltNo(FltCond fltCond);

    CommonResponse getByPlaceCond(FltCond fltCond,int page, int pageSize);

    CommonResponse getDetailByFfid(FltCond fltCond);
}
