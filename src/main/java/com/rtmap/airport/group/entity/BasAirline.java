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
 * @since 2022-03-01
 */
@Getter
@Setter
@TableName("ts_bas_airline")
@ApiModel(value = "BasAirline对象", description = "")
public class BasAirline implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("航空公司两字码")
    @TableId("airline_iata")
    private String airlineIata;

    @ApiModelProperty("航司三字码")
    @TableField("airline_icao")
    private String airlineIcao;

    @ApiModelProperty("航司中文全称")
    @TableField("airline_chn")
    private String airlineChn;

    @ApiModelProperty("航司中文简称")
    @TableField("airline_chn_brief")
    private String airlineChnBrief;

    @ApiModelProperty("航司英文名称")
    @TableField("airline_eng")
    private String airlineEng;

    @ApiModelProperty("全拼")
    @TableField("airline_nation")
    private String airlineNation;

    @ApiModelProperty("国际国内标识（D/I/R）")
    @TableField("domint")
    private String domint;

    @ApiModelProperty("航空公司运输业务分类(PA：客运；GD：货运；PG：客货混；BS：公务；GE：通航；OT：其他)")
    @TableField("airline_operation")
    private String airlineOperation;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;


}
