package com.rtmap.airport.group.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * 活动信息表
 * </p>
 *
 * @author shan
 * @since 2022-03-08
 */
@Getter
@Setter
@TableName("ts_activity")
@ApiModel(value = "Activity对象", description = "活动信息表")
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("机场三字码")
    @TableField("airport_code")
    private String airportCode;

    @ApiModelProperty("标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("封面图")
    @TableField("img_url")
    private String imgUrl;

    @ApiModelProperty("开始时间")
    @TableField("start_time")
    private LocalDateTime startTime;

    @ApiModelProperty("结束时间")
    @TableField("end_time")
    private LocalDateTime endTime;

    @ApiModelProperty("图文内容")
    @TableField("content")
    private String content;

    @ApiModelProperty("排序")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty("跳转链接")
    @TableField("link")
    private String link;

    @ApiModelProperty("0:未发布 1：已经发布 2：失效")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    @TableField("update_time")
    private LocalDateTime updateTime;


}
