package com.rtmap.airport.group.service;

import com.rtmap.airport.group.entity.Notice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rtmap.airport.group.result.CommonResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shan
 * @since 2022-02-22
 */
public interface INoticeService extends IService<Notice> {

    CommonResponse list(String airportCode,Integer page,Integer pageSize);
}
