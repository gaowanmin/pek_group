package com.rtmap.airport.group.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 机场集团功能明细
 * </p>
 *
 * @author cgk
 * @since 2022-03-02
 */
@Getter
@Setter
@TableName("ts_airport_group_multi_function_detail")
@ApiModel(value = "AirportGroupMultiFunctionDetail对象", description = "机场集团功能明细")
public class AirportGroupMultiFunctionDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("排序id")
    @TableField("sort_id")
    private Integer sortId;

    @ApiModelProperty("二级子项ID")
    @TableField("function_id")
    private Integer functionId;

    @ApiModelProperty("机场三字码")
    @TableField("airport_code")
    private String airportCode;

    @ApiModelProperty("细分标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("链接")
    @TableField("link")
    private String link;

    @ApiModelProperty("内容")
    @TableField("info")
    private String info;

    @ApiModelProperty("状态 0、开启 1、关闭")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private String name;

    @TableField(exist = false)
    private String imgDetail;




}
