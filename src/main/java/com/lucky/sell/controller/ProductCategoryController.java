package com.lucky.sell.controller;

import com.lucky.sell.domain.ProductCategory;
import com.lucky.sell.service.IProductCategoryService;
import com.lucky.sell.utils.ResultUtil;
import com.lucky.sell.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 * 类目表 前端控制器
 * </p>
 *
 * @author Ou Yang Xiao Guang
 * @since 2020-12-31
 */
@Api(tags = {"类目表"})
@RestController
@RequestMapping("/product-category")
public class ProductCategoryController {

    @Resource
    private IProductCategoryService productCategoryService;






}
