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
 * 商业店铺
 * </p>
 *
 * @author shan
 * @since 2022-02-24
 */
@Getter
@Setter
@TableName("bs_business")
@ApiModel(value = "Business对象", description = "商业店铺")
public class Business implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("机场三字码")
    @TableField("airport_code")
    private String airportCode;

    @ApiModelProperty("一级分类:shop购物，food餐饮")
    @TableField("primary_categroy")
    private String primaryCategroy;

    @ApiModelProperty("二级分类编码,支持逗号分隔")
    @TableField("second_categroy")
    private String secondCategroy;

    @ApiModelProperty("店铺名称")
    @TableField("store_name")
    private String storeName;

    @ApiModelProperty("航站楼")
    @TableField("terminal")
    private String terminal;

    @ApiModelProperty("楼层")
    @TableField("floor")
    private String floor;

    @ApiModelProperty("区域")
    @TableField("area")
    private String area;

    @ApiModelProperty("营业时间")
    @TableField("business_hours")
    private String businessHours;

    @ApiModelProperty("地址位置")
    @TableField("address")
    private String address;

    @ApiModelProperty("封面缩率图")
    @TableField("logo_pic")
    private String logoPic;

    @ApiModelProperty("详情页大图")
    @TableField("detail_pic")
    private String detailPic;

    @ApiModelProperty("联系电话")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("经营范围")
    @TableField("business_scope")
    private String businessScope;

    @ApiModelProperty("店铺描述")
    @TableField("store_desc")
    private String storeDesc;

    @ApiModelProperty("关键词搜索")
    @TableField("key_words")
    private String keyWords;

    @ApiModelProperty("人均价格")
    @TableField("per_price")
    private String perPrice;

    @ApiModelProperty("优惠信息描述")
    @TableField("discount_info")
    private String discountInfo;

    @ApiModelProperty("其他服务")
    @TableField("others_service")
    private String othersService;

    @ApiModelProperty("是否24小时店，0不是，1是")
    @TableField("flag_24hour")
    private Integer flag24hour;

    @ApiModelProperty("是否推荐店铺，0不推荐，1推荐")
    @TableField("flag_suggest")
    private Integer flagSuggest;

    @ApiModelProperty("室内地图建筑物id")
    @TableField("building_id")
    private String buildingId;

    @ApiModelProperty("室内地图楼层")
    @TableField("floor_no")
    private String floorNo;

    @TableField("x")
    private String x;

    @TableField("y")
    private String y;

    @ApiModelProperty("跳转地图链接")
    @TableField("map_url")
    private String mapUrl;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;


}
