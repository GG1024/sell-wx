package com.lucky.sell.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;


/**
 * <p>
 * 订单详情表
 * </p>
 *
 * @author Ou Yang Xiao Guang
 * @since 2020-12-31
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "订单详情表", description = "OrderDetail对象")
public class OrderDetail {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private String detailId;

    @ApiModelProperty(value = "订单id")
    private String orderId;

    @ApiModelProperty(value = "商品id")
    private String productId;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal productPrice;

    @ApiModelProperty(value = "商品数量")
    private Integer productQuantity;

    @ApiModelProperty(value = "图片链接")
    private String productIcon;


}
