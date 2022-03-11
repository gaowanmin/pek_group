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
 * @since 2022-02-28
 */
@Getter
@Setter
@TableName("ts_bas_airport")
@ApiModel(value = "BasAirport对象", description = "")
public class BasAirport implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("机场三字码")
    @TableField("airport_iata")
    private String airportIata;

    @ApiModelProperty("机场四字码")
    @TableId("airport_icao")
    private String airportIcao;

    @ApiModelProperty("中文全称")
    @TableField("airport_chn")
    private String airportChn;

    @ApiModelProperty("中文简称")
    @TableField("airport_chn_brief")
    private String airportChnBrief;

    @ApiModelProperty("英文名称")
    @TableField("airport_name_eng")
    private String airportNameEng;

    @ApiModelProperty("机场所属的国家")
    @TableField("airport_nation")
    private String airportNation;

    @ApiModelProperty("城市三字码")
    @TableField("airport_city")
    private String airportCity;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;


}
