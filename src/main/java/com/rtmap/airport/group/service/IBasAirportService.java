package com.rtmap.airport.group.service;

import com.rtmap.airport.group.entity.BasAirport;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rtmap.airport.group.result.CommonResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shan
 * @since 2022-02-28
 */
public interface IBasAirportService extends IService<BasAirport> {

    CommonResponse getAirport();
}
