package com.lucky.sell.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.sell.domain.OrderMaster;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.sell.domain.dto.OrderDTO;

import java.util.List;

/**
 * <p>
 * 订单主表 服务类
 * </p>
 *
 * @author Ou Yang Xiao Guang
 * @since 2020-12-31
 */
public interface IOrderMasterService extends IService<OrderMaster> {



    /**
     * 查询订单详情
     *
     * @param orderId
     * @return
     */
    OrderDTO findOne(String orderId);

    Page<OrderDTO> findList(Integer page, Integer size);

    Page<OrderDTO> findList(String buyerOpenid, Integer page, Integer size);

    /**
     * 取消订单
     *
     * @param orderDTO
     * @return
     */
    OrderDTO cancel(OrderDTO orderDTO);

    /**
     * 完结订单
     *
     * @param orderDTO
     * @return
     */
    OrderDTO finish(OrderDTO orderDTO);

    /**
     * 修改订单的支付状态
     * @param orderDTO
     * @return
     */
    OrderDTO paid(OrderDTO orderDTO);

    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);
}

