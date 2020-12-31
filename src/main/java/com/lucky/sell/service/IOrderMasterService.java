package com.lucky.sell.service;

import com.lucky.sell.domain.OrderMaster;
import com.baomidou.mybatisplus.extension.service.IService;

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
     * 查询订单主表模块
     *
     * @param id 订单主表模块ID
     * @return 订单主表模块
     */
    OrderMaster selectById(String id);

    /**
     * 查询订单主表模块列表
     *
     * @param orderMaster 订单主表模块
     * @return 订单主表模块集合
     */
    List<OrderMaster> selectList(OrderMaster orderMaster);

    /**
     * 新增订单主表模块
     *
     * @param orderMaster 订单主表模块
     * @return 结果
     */
    int insert(OrderMaster orderMaster);

    /**
     * 修改订单主表模块
     *
     * @param orderMaster 订单主表模块
     * @return 结果
     */
    int update(OrderMaster orderMaster);

    /**
     * 批量删除订单主表模块
     *
     * @param ids 需要删除的订单主表模块ID
     * @return 结果
     */
    int deleteByIds(List<String> ids);

    /**
     * 删除订单主表模块信息
     *
     * @param id 订单主表模块ID
     * @return 结果
     */
    int deleteById(String id);


}

