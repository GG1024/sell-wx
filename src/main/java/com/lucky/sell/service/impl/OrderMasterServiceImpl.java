package com.lucky.sell.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.sell.converter.OrderMaster2OrderDTOConverter;
import com.lucky.sell.domain.OrderDetail;
import com.lucky.sell.domain.OrderMaster;
import com.lucky.sell.domain.ProductInfo;
import com.lucky.sell.domain.dto.CartDTO;
import com.lucky.sell.domain.dto.OrderDTO;
import com.lucky.sell.enums.OrderStatusEnum;
import com.lucky.sell.enums.PayStatusEnum;
import com.lucky.sell.enums.ResultEnum;
import com.lucky.sell.exception.SellException;
import com.lucky.sell.mapper.OrderDetailMapper;
import com.lucky.sell.mapper.OrderMasterMapper;
import com.lucky.sell.service.*;
import com.lucky.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    private IProductInfoService iProductInfoService;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private PushMessageService pushMessageService;

    @Autowired
    private PayService payService;

    @Autowired
    private WebSocket webSocket;


    @Override
    public OrderDTO findOne(String orderId) {
        QueryWrapper<OrderMaster> orderMasterQueryWrapper = new QueryWrapper<>();
        orderMasterQueryWrapper.lambda().eq(OrderMaster::getOrderId, orderId);
        OrderMaster orderMaster = baseMapper.selectOne(orderMasterQueryWrapper);
        if (orderMaster == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        QueryWrapper<OrderDetail> where = new QueryWrapper<>();
        where.lambda().eq(OrderDetail::getOrderId, orderId);
        List<OrderDetail> orderDetailList = orderDetailMapper.selectList(where);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(Integer page, Integer size) {
        /**
         * 1.分页查询
         * 2.封装商品
         * 3.封装分页
         */

        IPage<OrderMaster> orderMasterPage = baseMapper.selectPage(new Page<>(page, size), new QueryWrapper<>());
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getRecords());
        Page resultPage = new Page();
        resultPage.setCurrent(page);
        resultPage.setSize(size);
        resultPage.setTotal(orderMasterPage.getTotal());
        resultPage.setRecords(orderDTOList);
        return resultPage;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Integer page, Integer size) {
        QueryWrapper<OrderMaster> where = new QueryWrapper<>();
        where.lambda().eq(OrderMaster::getBuyerOpenid, buyerOpenid);
        IPage<OrderMaster> orderMasterPage = baseMapper.selectPage(new Page<>(page, size), where);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getRecords());
        Page resultPage = new Page();
        resultPage.setCurrent(page);
        resultPage.setSize(size);
        resultPage.setTotal(orderMasterPage.getTotal());
        resultPage.setRecords(orderDTOList);
        return resultPage;
    }

    /**
     * 取消订单
     *
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();

        /**
         * 1.判断订单状态
         * 2.修改订单状态
         * 3.返回库存
         * 4.如果已支付需要退款
         */
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【完结订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        int resultCode = update(orderMaster);
        if (resultCode == 0) {
            log.error("【取消订单】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【取消订单】订单中无商品详情, orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }

        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        iProductInfoService.increaseStock(cartDTOList);


        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            payService.refund(orderDTO);
        }

        return orderDTO;
    }


    /**
     * 完结订单
     *
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        /**
         * 1.判断订单状态
         * 2.修改订单状态
         * 3.推送微信模板消息
         */
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【完结订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        int resultCode = update(orderMaster);
        if (resultCode == 0) {
            log.error("【完结订单】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        pushMessageService.orderStatus(orderDTO);

        return orderDTO;
    }

    /**
     * 修改订单的支付状态
     *
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        /**
         * 1.判断订单状态
         * 2.判断支付状态
         * 3.修改支付状态
         */
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【订单支付完成】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("【订单支付完成】订单支付状态不正确, orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        int resultCode =update(orderMaster);
        if (resultCode == 0) {
            log.error("【订单支付完成】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }


    /**
     * 创建订单
     *
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        //查询商品（数量，价格）
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            //根据商品ID查询商品
            Optional<ProductInfo> productInfo = Optional.ofNullable(iProductInfoService.findOne(orderDetail.getProductId()));
            if (!productInfo.isPresent()) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //计算订单总价
            orderAmount = productInfo.get().getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);

            //订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo.get(), orderDetail);
            orderDetailMapper.insert(orderDetail);
        }

        // 写入订单数据库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        baseMapper.insert(orderMaster);

        //扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        iProductInfoService.decreaseStock(cartDTOList);


        //发送websocket消息
        webSocket.sendMessage(orderDTO.getOrderId());
        return orderDTO;
    }


    private int update(OrderMaster orderMaster) {
        QueryWrapper<OrderMaster> where = new QueryWrapper<>();
        where.lambda().eq(OrderMaster::getOrderId, orderMaster.getOrderId());
        return baseMapper.update(orderMaster, where);
    }
}

