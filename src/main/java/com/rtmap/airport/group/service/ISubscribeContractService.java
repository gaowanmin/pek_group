package com.rtmap.airport.group.service;

import com.rtmap.airport.group.entity.SubscribeContract;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rtmap.airport.group.entity.cond.SubScribeCond;
import com.rtmap.airport.group.result.CommonResponse;

/**
 * <p>
 * 订阅契约表 服务类
 * </p>
 *
 * @author shan
 * @since 2022-03-03
 */
public interface ISubscribeContractService extends IService<SubscribeContract> {

    CommonResponse subScribeFlt(SubscribeContract subscribeContract);

    CommonResponse getFltBySubScribe(SubScribeCond subScribeCond);

    CommonResponse cancelSubScribeFlt(SubScribeCond subScribeCond);

}
