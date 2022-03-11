package com.rtmap.airport.group.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 订阅契约表
 * </p>
 *
 * @author shan
 * @since 2022-03-03
 */
@Getter
@Setter
@TableName("subscribe_contract")
@ApiModel(value = "SubscribeContract对象", description = "订阅契约表")
public class SubscribeContract implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订阅契约表Id")
    @TableId(value = "contract_id", type = IdType.AUTO)
    private Integer contractId;

    @ApiModelProperty("订阅者Id")
    @TableField("opendid")
    private String opendid;

    @ApiModelProperty("订阅航班唯一标识")
    @TableField("ffid")
    private String ffid;

    @ApiModelProperty("航班号")
    @TableField("flt_no")
    private String fltNo;

    @ApiModelProperty("航班日期")
    @TableField("flt_date")
    private LocalDate fltDate;

    @ApiModelProperty("航司二字码")
    @TableField("iata")
    private String iata;

    @ApiModelProperty("进出港标识")
    @TableField("aord")
    private String aord;

    @ApiModelProperty("国际国内标识")
    @TableField("domint")
    private String domint;

    @ApiModelProperty("订阅状态，默认Y已订阅，取消订阅的C")
    @TableField("subscribe_status")
    private String subscribeStatus;

    @ApiModelProperty("订阅失效时间默认+48小时")
    @TableField("invalid_time")
    private LocalDateTime invalidTime;

    @ApiModelProperty("首次订阅契约发生时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("最近更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;


}
