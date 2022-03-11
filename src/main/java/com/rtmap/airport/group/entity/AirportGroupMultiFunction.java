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
 * 机场集团多功能
 * </p>
 *
 * @author cgk
 * @since 2022-02-24
 */
@Getter
@Setter
@TableName("ts_airport_group_multi_function")
@ApiModel(value = "AirportGroupMultiFunction对象", description = "机场集团多功能")
public class AirportGroupMultiFunction implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("类型ID：1、常用服务 2、爱心服务 3、应急服务 4、便利设施 5、行李服务 6、交通服务 7、乘机须知 8、出行指南 9、辅助服务 10、出发 11、到达 12、中转")
    @TableField("type_id")
    private Integer typeId;

    @ApiModelProperty("排序id")
    @TableField("sort_id")
    private Integer sortId;

    @ApiModelProperty("机场三字码")
    @TableField("airport_code")
    private String airportCode;

    @ApiModelProperty("标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("图片")
    @TableField("icon")
    private String icon;

    @ApiModelProperty("详情页图片")
    @TableField("img_detail")
    private String imgDetail;

    @ApiModelProperty("子项图片，主用于行程服务推荐图片")
    @TableField("img")
    private String img;

    @ApiModelProperty("链接")
    @TableField("link")
    private String link;

    @ApiModelProperty("描述信息")
    @TableField("info")
    private String info;

    @ApiModelProperty("第三方跳转ID")
    @TableField("app_id")
    private String appId;

    @ApiModelProperty("状态 0、开启 1、关闭")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("是否特色: T：是 F：否")
    @TableField("is_characteristic")
    private String isCharacteristic;

//    @ApiModelProperty("出行决策：D:出发 、A:到达、T:中转")
//    @TableField("travel_decision")
//    private String travelDecision;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    @TableField("update_time")
    private LocalDateTime updateTime;


}
