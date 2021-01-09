package com.lucky.sell.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * <p>
 *
 * </p>
 *
 * @author Ou Yang Xiao Guang
 * @since 2020-12-31
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "", description = "SellerInfo对象")
public class SellerInfo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private String sellerId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "openID")
    private String openid;


}
