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
 * 商贸餐饮二级分类表
 * </p>
 *
 * @author shan
 * @since 2022-03-08
 */
@Getter
@Setter
@TableName("bs_category")
@ApiModel(value = "Category对象", description = "商贸餐饮二级分类表")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("一级分类代码")
    @TableField("primary_category")
    private String primaryCategory;

    @ApiModelProperty("二级分类代码")
    @TableField("second_category")
    private String secondCategory;

    @ApiModelProperty("二级分类名称")
    @TableField("second_category_name")
    private String secondCategoryName;

    @ApiModelProperty("状态:0未启用1启用")
    @TableField("status")
    private Integer status;


}
