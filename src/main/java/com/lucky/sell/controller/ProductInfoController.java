package com.lucky.sell.controller;

import com.lucky.sell.service.IProductInfoService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author Ou Yang Xiao Guang
 * @since 2020-12-31
 */
@Api(tags = {"商品表"})
@RestController
@RequestMapping("/product-info")
public class ProductInfoController {

    @Resource
    private IProductInfoService productInfoService;



}
