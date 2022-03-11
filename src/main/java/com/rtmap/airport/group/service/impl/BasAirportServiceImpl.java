package com.rtmap.airport.group.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rtmap.airport.group.conf.RedisUtils;
import com.rtmap.airport.group.entity.BasAirport;
import com.rtmap.airport.group.entity.Notice;
import com.rtmap.airport.group.enums.DomintConstant;
import com.rtmap.airport.group.enums.RedisKeyConstant;
import com.rtmap.airport.group.mapper.BasAirportMapper;
import com.rtmap.airport.group.mapper.HotAirportMapper;
import com.rtmap.airport.group.result.CommonResponse;
import com.rtmap.airport.group.service.IBasAirportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rtmap.airport.group.util.HanyuPinyinHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shan
 * @since 2022-02-28
 */
@Service
public class BasAirportServiceImpl extends ServiceImpl<BasAirportMapper, BasAirport> implements IBasAirportService {

    private static final char[] engsC = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    @Autowired
    private BasAirportMapper basAirportMapper;

    @Resource
    private RedisUtils redisUtils;


    @Override
    public CommonResponse getAirport() {
        String airports = redisUtils .get(RedisKeyConstant.Airport.BASE_AIRPORT);
        if (airports != null) {
            return new CommonResponse(airports);
        }
//        QueryWrapper<BasAirport> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("airport_international_or_domestic",DomintConstant.D);
        // 查询国内机场
//        List<BasAirport> domintAirports = basAirportMapper.selectList(queryWrapper);
        List<BasAirport> domintAirports = basAirportMapper.selectSimpleProperties(DomintConstant.D);

        // 查询国际机场
//        queryWrapper.eq("airport_international_or_domestic",DomintConstant.I);
//        List<BasAirport> interAirports = basAirportMapper.selectList(queryWrapper);
        List<BasAirport> interAirports = basAirportMapper.selectSimpleProperties(DomintConstant.I);
        Map<String, ArrayList<BasAirport>> domeMap = classifyAirports(domintAirports);
        Map<String, ArrayList<BasAirport>> interMap = classifyAirports(interAirports);
        Map<String, Map<String, ArrayList<BasAirport>>> map = new HashMap<>();
        map.put("domestic", domeMap);
        map.put("international", interMap);
        redisUtils.setWithTimeOut(RedisKeyConstant.Airport.BASE_AIRPORT, JSONObject.toJSONString(map),60 * 60 * 24);
        return new CommonResponse (JSONObject.toJSONString(map));

    }

    /**
     * 基础机场分类
     */
    Map<String, ArrayList<BasAirport>> classifyAirports(List<BasAirport> basAirports) {
        Map<String, ArrayList<BasAirport>> foreignOrDomesticMap = new LinkedHashMap<>();
        for (int i = 0; i < engsC.length; i++) {
            ArrayList<BasAirport> list = new ArrayList<>();
            for (int j = 0; j < basAirports.size(); j++) {
                BasAirport airport = basAirports.get(j);
                char c = 0;
                    if (!StringUtils.isEmpty(airport.getAirportChnBrief()))
                        c = HanyuPinyinHelper.getPinyinString(airport.getAirportChnBrief()).toUpperCase().charAt(0);

                if (c == engsC[i]) {
                    list.add(basAirports.get(j));
                    basAirports.remove(j);
                    j--;
                }
            }
            if (!list.isEmpty()) {
                foreignOrDomesticMap.put(new String(new char[]{engsC[i]}), list);
            }
        }
        return foreignOrDomesticMap;
    }
}
