package com.rtmap.airport.group.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 * 商业店铺
 * </p>
 *
 * @author shan
 * @since 2022-02-24
 */
@Data
public class BusinessSearchVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("机场三字码")
    @NotBlank(message = "机场三字码不能为空")
    private String airportCode;

    @ApiModelProperty("一级分类:shop购物，food餐饮")
    private String primaryCategroy;

    @ApiModelProperty("二级分类编码")
    private String secondCategroy;

    @ApiModelProperty("航站楼")
    private String terminal;

    @ApiModelProperty("楼层")
    private String floor;

    @ApiModelProperty("区域：安检区外、安检区内")
    private String area;

    @ApiModelProperty("关键词搜索")
    private String keyWords;

    @ApiModelProperty("是否24小时店，0不是，1是")
    private Integer flag24hour;

    @ApiModelProperty("是否推荐店铺，0不推荐，1推荐")
    private Integer flagSuggest;

    @ApiModelProperty("店铺标签")
    private String othersService;

    @ApiModelProperty("页码")
    private Integer page = 0;

    @ApiModelProperty("分页大小")
    private Integer pageSize = 20;

}
