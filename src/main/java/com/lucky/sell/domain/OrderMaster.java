package com.lucky.sell.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;


/**
 * <p>
 * 订单主表
 * </p>
 *
 * @author Ou Yang Xiao Guang
 * @since 2020-12-31
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "订单主表", description = "OrderMaster对象")
public class OrderMaster {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private String orderId;

    @ApiModelProperty(value = "买家名字")
    private String buyerName;

    @ApiModelProperty(value = "买家电话")
    private String buyerPhone;

    @ApiModelProperty(value = "买家地址")
    private String buyerAddress;

    @ApiModelProperty(value = "买家openID")
    private String buyerOpenid;

    @ApiModelProperty(value = "订单总金额")
    private BigDecimal orderAmount;

    @ApiModelProperty(value = "订单状态默认0：新订单")
    private Integer orderStatus;

    @ApiModelProperty(value = "支付状态默认0：未支付")
    private Integer payStatus;


}
