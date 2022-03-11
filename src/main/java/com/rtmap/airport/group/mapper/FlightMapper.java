package com.rtmap.airport.group.mapper;

import com.rtmap.airport.group.entity.Flight;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gaowm
 * @since 2022-02-28
 */
@Mapper
public interface FlightMapper extends BaseMapper<Flight> {

    @Select("<script>" +
            "select * from flight where unit_ufi in\n" +
            "<foreach collection=\"ffidsList\" item= \"item\" index =\"index\"\n" +
            "open= \"(\" close =\")\" separator=\",\">\n" +
            "#{item}\n" +
            "</foreach >" +
            "</script>")
    List<Flight> selectByFfids(@Param("ffidsList") List<String> ffidsList);

}
