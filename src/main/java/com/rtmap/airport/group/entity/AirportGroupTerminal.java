package com.rtmap.airport.group.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 机场航站楼和楼层对应表
 * </p>
 *
 * @author shan
 * @since 2022-02-24
 */
@Getter
@Setter
@TableName("ts_airport_group_terminal")
@ApiModel(value = "AirportGroupTerminal对象", description = "机场航站楼和楼层对应表")
public class AirportGroupTerminal implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("机场三字码")
    @TableField("airport_code")
    private String airportCode;

    @ApiModelProperty("航站楼名称")
    @TableField("terminal")
    private String terminal;

    @ApiModelProperty("楼层")
    @TableField("floor")
    private String floor;

    @ApiModelProperty("状态0:下线，1:上线 ")
    @TableField("status")
    private Integer status;


}
