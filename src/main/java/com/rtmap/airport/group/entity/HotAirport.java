package com.rtmap.airport.group.entity;

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
 * 中文全称
 * </p>
 *
 * @author shan
 * @since 2022-02-28
 */
@Getter
@Setter
@TableName("hot_airport")
@ApiModel(value = "HotAirport对象", description = "中文全称")
public class HotAirport implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("热门机场Id")
    @TableId("id")
    private Integer id;

    @ApiModelProperty("视角机场三字码")
    @TableField("curr_airport_code")
    private String currAirportCode;

    @ApiModelProperty("机场三字码")
    @TableField("airport_code")
    private String airportCode;

    @ApiModelProperty("城市国际国内标识 D|I")
    @TableField("domint")
    private String domint;

    @ApiModelProperty("当前结果集显示序号")
    @TableField("priority")
    private Integer priority;

    @ApiModelProperty("中文全称")
    @TableField("name_cn")
    private String nameCn;

    @ApiModelProperty("英文全称")
    @TableField("name_en")
    private String nameEn;


}
