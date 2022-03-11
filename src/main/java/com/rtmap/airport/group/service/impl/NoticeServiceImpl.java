package com.rtmap.airport.group.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rtmap.airport.group.entity.Notice;
import com.rtmap.airport.group.enums.NoticeStatusType;
import com.rtmap.airport.group.mapper.NoticeMapper;
import com.rtmap.airport.group.result.CommonResponse;
import com.rtmap.airport.group.service.INoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author shan
 * @since 2022-02-22
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

    @Resource
    private NoticeMapper noticeMapper;

    @Override
    public CommonResponse list(String airportCode, Integer page, Integer pageSize) {
        Page<Notice> noticePage = new Page<>(page, pageSize);
        QueryWrapper<Notice> wrapper = new QueryWrapper<>();
        wrapper.eq("airport_code", airportCode.toUpperCase());
        wrapper.eq("status", NoticeStatusType.PUBLISH.getStatus());
        IPage<Notice> noticeIPage = noticeMapper.selectPage(noticePage, wrapper);
        return new CommonResponse(noticeIPage);
    }
}
