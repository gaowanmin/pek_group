package com.rtmap.airport.group.entity.cond;

import lombok.Data;

/**
 * @author: gaowm
 * @date: 2018/11/30
 * @desc: 航班查询参数对象
 */
@Data
public class FltCond {
    /** 航班唯一标识*/
    private String fltId;

    /** 航班唯一标识*/
    private String ffid;

    /** 进出港标识*/
    private String aord;

    /** 查询日期*/
    private String queryDate;

    /** 航班号*/
    private String fltNo;

    /** 机场三字码*/
    private String airportCode;

    /** 当前视角机场三字码*/
    private String currAirportCode;

    private String openid;
}
