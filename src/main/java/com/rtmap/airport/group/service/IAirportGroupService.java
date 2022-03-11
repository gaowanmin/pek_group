package com.rtmap.airport.group.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.rtmap.airport.group.entity.AirportGroup;
import com.rtmap.airport.group.result.CommonResponse;

/**
 * 集团机场
 *
 * @Auther: shan
 * @Date:2022/2/16
 * @Description:
 */
public interface IAirportGroupService extends IService<AirportGroup> {

    CommonResponse list(Double lat, Double lng, int page, int size);

    CommonResponse detail(String airportCode);

}
