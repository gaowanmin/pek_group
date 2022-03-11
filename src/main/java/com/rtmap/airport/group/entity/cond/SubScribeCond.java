package com.rtmap.airport.group.entity.cond;

import lombok.Data;

/**
 * @author: gaowm
 * @date: 2018/12/7
 * @desc: 订阅条件对象
 */
@Data
public class SubScribeCond {

    private String openid;

    private String ffid;

    private Integer contractId;
}
