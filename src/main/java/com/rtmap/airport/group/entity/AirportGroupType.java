package com.rtmap.airport.group.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 首页一级功能列表
 * </p>
 *
 * @author cgk
 * @since 2022-02-28
 */
@Getter
@Setter
@TableName("ts_airport_group_type")
@ApiModel(value = "AirportGroupType对象", description = "首页一级功能列表")
public class AirportGroupType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("类型编码")
    @TableField("type_code")
    private String typeCode;

    @ApiModelProperty("排序id")
    @TableField("sort_id")
    private Integer sortId;

    @ApiModelProperty("标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("信息描述")
    @TableField("info")
    private String info;

    @ApiModelProperty("状态")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("机场三字码")
    @TableField("airport_code")
    private String airportCode;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField(exist = false)
    List<AirportGroupMultiFunction> multiFunctions;


}
