package com.rtmap.airport.group.service;

import com.rtmap.airport.group.entity.HotAirport;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rtmap.airport.group.result.CommonResponse;

/**
 * <p>
 * 中文全称 服务类
 * </p>
 *
 * @author shan
 * @since 2022-02-28
 */
public interface IHotAirportService extends IService<HotAirport> {

    CommonResponse getHotAirport(String airportCode);
}
