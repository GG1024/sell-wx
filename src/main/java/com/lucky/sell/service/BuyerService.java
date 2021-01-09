package com.lucky.sell.service;

import com.lucky.sell.domain.dto.OrderDTO;

/**
 * @FileName: BuyerService.java
 * @description: 买家
 * @author: OuYangXiaoGuang
 * @Date: 2021-01-04 10:11
 **/
public interface BuyerService {

    //查询一个订单
    OrderDTO findOrderOne(String openid, String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid, String orderId);
}
