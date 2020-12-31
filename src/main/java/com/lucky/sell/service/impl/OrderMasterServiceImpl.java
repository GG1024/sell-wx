package com.lucky.sell.service.impl;

import com.lucky.sell.domain.OrderMaster;
import com.lucky.sell.mapper.OrderMasterMapper;
import com.lucky.sell.service.IOrderMasterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.List;

/**
 * <p>
 * 订单主表 服务实现类
 * </p>
 *
 * @author Ou Yang Xiao Guang
 * @since 2020-12-31
 */
@Service
@Slf4j
public class OrderMasterServiceImpl extends ServiceImpl<OrderMasterMapper, OrderMaster> implements IOrderMasterService {


    /**
     * 查询订单主表模块
     *
     * @param id 订单主表模块ID
     * @return 订单主表模块
     */
    @Override
    public OrderMaster selectById(String id) {
        return baseMapper.selectById(id);
    }

    /**
     * 查询订单主表模块列表
     *
     * @param orderMaster 订单主表模块
     * @return 订单主表模块
     */
    @Override
    public List<OrderMaster> selectList(OrderMaster orderMaster) {
        QueryWrapper<OrderMaster> where = new QueryWrapper<>();
        where.setEntity(orderMaster);
        return baseMapper.selectList(where);
    }

    /**
     * 新增订单主表模块
     *
     * @param orderMaster 订单主表模块
     * @return 结果
     */
    @Override
    public int insert(OrderMaster orderMaster) {
        return baseMapper.insert(orderMaster);
    }

    /**
     * 修改订单主表模块
     *
     * @param orderMaster 订单主表模块
     * @return 结果
     */
    @Override
    public int update(OrderMaster orderMaster) {
        return baseMapper.updateById(orderMaster);
    }

    /**
     * 批量删除订单主表模块
     *
     * @param ids 需要删除的订单主表模块ID
     * @return 结果
     */
    @Override
    public int deleteByIds(List<String> ids) {
        return baseMapper.deleteBatchIds(ids);
    }

    /**
     * 删除订单主表模块信息
     *
     * @param id 订单主表模块ID
     * @return 结果
     */
    @Override
    public int deleteById(String id) {
        return baseMapper.deleteById(id);
    }


}

