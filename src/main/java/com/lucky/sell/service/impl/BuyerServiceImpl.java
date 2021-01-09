package com.lucky.sell.service.impl;

import com.lucky.sell.domain.dto.OrderDTO;
import com.lucky.sell.enums.ResultEnum;
import com.lucky.sell.exception.SellException;
import com.lucky.sell.service.BuyerService;
import com.lucky.sell.service.IOrderMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @FileName: BuyerServiceImpl.java
 * @description: 买家
 * @author: OuYangXiaoGuang
 * @Date: 2021-01-04 10:12
 **/
@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private IOrderMasterService iOrderMasterService;


    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (orderDTO == null) {
            log.error("【取消订单】查不到改订单, orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return iOrderMasterService.cancel(orderDTO);
    }


    /**
     * 检测订单
     **/
    private OrderDTO checkOrderOwner(String openid, String orderId) {
        Optional<OrderDTO> orderDTO = Optional.ofNullable(iOrderMasterService.findOne(orderId));
        if (orderDTO.isPresent()) {
            return orderDTO.orElse(null);
        }

        if (!orderDTO.get().getBuyerOpenid().equalsIgnoreCase(openid)) {
            log.error("【查询订单】订单的openid不一致. openid={}, orderDTO={}", openid, orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO.get();
    }

}
