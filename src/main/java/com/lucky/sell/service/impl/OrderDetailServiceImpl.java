package com.lucky.sell.service.impl;

import com.lucky.sell.domain.OrderDetail;
import com.lucky.sell.mapper.OrderDetailMapper;
import com.lucky.sell.service.IOrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.List;

/**
 * <p>
 * 订单详情表 服务实现类
 * </p>
 *
 * @author Ou Yang Xiao Guang
 * @since 2020-12-31
 */
@Service
@Slf4j
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements IOrderDetailService {


    /**
     * 查询订单详情表模块
     *
     * @param id 订单详情表模块ID
     * @return 订单详情表模块
     */
    @Override
    public OrderDetail selectById(String id) {
        return baseMapper.selectById(id);
    }

    /**
     * 查询订单详情表模块列表
     *
     * @param orderDetail 订单详情表模块
     * @return 订单详情表模块
     */
    @Override
    public List<OrderDetail> selectList(OrderDetail orderDetail) {
        QueryWrapper<OrderDetail> where = new QueryWrapper<>();
        where.setEntity(orderDetail);
        return baseMapper.selectList(where);
    }

    /**
     * 新增订单详情表模块
     *
     * @param orderDetail 订单详情表模块
     * @return 结果
     */
    @Override
    public int insert(OrderDetail orderDetail) {
        return baseMapper.insert(orderDetail);
    }

    /**
     * 修改订单详情表模块
     *
     * @param orderDetail 订单详情表模块
     * @return 结果
     */
    @Override
    public int update(OrderDetail orderDetail) {
        return baseMapper.updateById(orderDetail);
    }

    /**
     * 批量删除订单详情表模块
     *
     * @param ids 需要删除的订单详情表模块ID
     * @return 结果
     */
    @Override
    public int deleteByIds(List<String> ids) {
        return baseMapper.deleteBatchIds(ids);
    }

    /**
     * 删除订单详情表模块信息
     *
     * @param id 订单详情表模块ID
     * @return 结果
     */
    @Override
    public int deleteById(String id) {
        return baseMapper.deleteById(id);
    }


}

