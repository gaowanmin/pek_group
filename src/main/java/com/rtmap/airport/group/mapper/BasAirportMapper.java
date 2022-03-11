package com.rtmap.airport.group.mapper;

import com.rtmap.airport.group.entity.BasAirport;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shan
 * @since 2022-02-28
 */
@Mapper
public interface BasAirportMapper extends BaseMapper<BasAirport> {

    @Select("SELECT airport_iata, airport_icao, airport_chn, airport_chn_brief, airport_name_eng, airport_international_or_domestic FROM ts_bas_airport WHERE airport_international_or_domestic = #{domint}")
    @ResultType(BasAirport.class)
    List<BasAirport> selectSimpleProperties(String domint);
}
