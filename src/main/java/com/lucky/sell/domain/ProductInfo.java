package com.lucky.sell.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;


/**
 * <p>
 * 商品表
 * </p>
 *
 * @author Ou Yang Xiao Guang
 * @since 2020-12-31
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "商品表", description = "ProductInfo对象")
public class ProductInfo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private String productId;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal productPrice;

    @ApiModelProperty(value = "库存")
    private Integer productStock;

    @ApiModelProperty(value = "商品描述")
    private String productDesc;

    @ApiModelProperty(value = "类目类型")
    private Integer categoryType;

    @ApiModelProperty(value = "图片链接")
    private String productIcon;


}
