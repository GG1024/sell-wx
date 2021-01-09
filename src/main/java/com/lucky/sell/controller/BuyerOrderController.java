package com.lucky.sell.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.sell.converter.OrderForm2OrderDTOConverter;
import com.lucky.sell.domain.dto.OrderDTO;
import com.lucky.sell.domain.form.OrderForm;
import com.lucky.sell.enums.ResultEnum;
import com.lucky.sell.exception.SellException;
import com.lucky.sell.service.BuyerService;
import com.lucky.sell.service.IOrderMasterService;
import com.lucky.sell.utils.ResultUtil;
import com.lucky.sell.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 廖师兄
 * 2017-06-18 23:27
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private IOrderMasterService iOrderMasterService;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    public Result<Map<String, String>> create(@Valid OrderForm orderForm,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = iOrderMasterService.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return ResultUtil.success(map);
    }

    //订单列表
    @GetMapping("/list")
    public Result<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                       @RequestParam(value = "page", defaultValue = "0") Integer page,
                                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

//        PageRequest request = PageRequest.of();
        Page<OrderDTO> orderDTOPage = iOrderMasterService.findList(openid, page, size);

        return ResultUtil.success(orderDTOPage.getRecords());
    }


    //订单详情
    @GetMapping("/detail")
    public Result<OrderDTO> detail(@RequestParam("openid") String openid,
                                   @RequestParam("orderId") String orderId) {
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        return ResultUtil.success(orderDTO);
    }

    //取消订单
    @PostMapping("/cancel")
    public Result cancel(@RequestParam("openid") String openid,
                         @RequestParam("orderId") String orderId) {
        buyerService.cancelOrder(openid, orderId);
        return ResultUtil.success();
    }
}
