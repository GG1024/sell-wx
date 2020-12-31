package com.lucky.sell.service;

import com.lucky.sell.domain.OrderDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 订单详情表 服务类
 * </p>
 *
 * @author Ou Yang Xiao Guang
 * @since 2020-12-31
 */
public interface IOrderDetailService extends IService<OrderDetail> {

    /**
     * 查询订单详情表模块
     *
     * @param id 订单详情表模块ID
     * @return 订单详情表模块
     */
    OrderDetail selectById(String id);

    /**
     * 查询订单详情表模块列表
     *
     * @param orderDetail 订单详情表模块
     * @return 订单详情表模块集合
     */
    List<OrderDetail> selectList(OrderDetail orderDetail);

    /**
     * 新增订单详情表模块
     *
     * @param orderDetail 订单详情表模块
     * @return 结果
     */
    int insert(OrderDetail orderDetail);

    /**
     * 修改订单详情表模块
     *
     * @param orderDetail 订单详情表模块
     * @return 结果
     */
    int update(OrderDetail orderDetail);

    /**
     * 批量删除订单详情表模块
     *
     * @param ids 需要删除的订单详情表模块ID
     * @return 结果
     */
    int deleteByIds(List<String> ids);

    /**
     * 删除订单详情表模块信息
     *
     * @param id 订单详情表模块ID
     * @return 结果
     */
    int deleteById(String id);


}

