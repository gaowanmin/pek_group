package com.rtmap.airport.group.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author shan
 * @since 2022-03-07
 */
@Getter
@Setter
@TableName("ts_weather")
@ApiModel(value = "Weather对象", description = "")
public class Weather implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("机场三字码")
    @TableId("airport_code")
    private String airportCode;

    @ApiModelProperty("机场简称")
    @TableField("ariport_name_abbr")
    private String ariportNameAbbr;

    @ApiModelProperty("机场全称")
    @TableField("airprot_name")
    private String airprotName;

    @ApiModelProperty("PM2.5")
    @TableField("pm")
    private String pm;

    @ApiModelProperty("摄氏温度")
    @TableField("temperature")
    private String temperature;

    @ApiModelProperty("天气描述")
    @TableField("weather")
    private String weather;

    @ApiModelProperty("发布时间")
    @TableField("publish_time")
    private String publishTime;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;


}
