package com.rtmap.airport.group.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 机场集团支持的机场
 *
 * @Auther: shan
 * @Date:2022/2/17
 * @Description:
 */
@Data
@TableName("ts_airport_group")
public class AirportGroup implements Comparable<AirportGroup> {

    private Integer id;

    private String airportCode;

    private String name;

    private String englishName;

    private String address;

    /**
     * 纬度
     */
    private Double lat;

    /**
     * 经度
     */
    private Double lng;

    private String cityCode;

    private String areaCode;

    private String mobile;

    private String phone;

    private String logo;

    private String status;

    @TableField(exist = false)
    private Double distance = 100.0;

    @TableField(exist = false)
    Map<String, List<AirportGroupTerminal>> airportGroupTerminal;

    @Override
    public int compareTo(AirportGroup arg0) {
        return this.getDistance().compareTo(arg0.getDistance());
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }

    @Override
    public int hashCode() {
        return 1;
    }

}
