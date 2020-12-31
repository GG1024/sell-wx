package com.lucky.sell.controller;

import com.lucky.sell.domain.ProductInfo;
import com.lucky.sell.service.IProductInfoService;
import com.lucky.sell.utils.ResultUtil;
import com.lucky.sell.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


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

    /**
     * 查询商品表模块列表
     */
  /*  @GetMapping("/list")
    @ApiOperation(value = "查询商品表模块列表", notes = "查询商品表模块列表")
    public TableDataInfo list(ProductInfo productInfo) {
        startPage();
        List<ProductInfo> list = productInfoService.selectList(productInfo);
        return getDataTable(list);
    }*/


    /**
     * 获取商品表模块详细信息
     */
    @GetMapping(value = "getInfo/{id}")
    @ApiOperation(value = "获取商品表模块详细信息", notes = "获取商品表模块详细信息")
    public Result getInfo(@PathVariable("id") String id) {
        return ResultUtil.success(productInfoService.selectById(id));
    }

    /**
     * 新增商品表模块
     */
    @PostMapping("add")
    @ApiOperation(value = "新增商品表模块", notes = "新增商品表模块")
    public Result add(@RequestBody ProductInfo productInfo) {
        return ResultUtil.success(productInfoService.insert(productInfo));
    }

    /**
     * 修改商品表模块
     */
    @PutMapping("edit")
    @ApiOperation(value = " 修改商品表模块", notes = "修改商品表模块")
    public Result edit(@RequestBody ProductInfo productInfo) {
        return ResultUtil.success(productInfoService.update(productInfo));
    }

    /**
     * 删除商品表模块
     */
    @DeleteMapping("remove")
    @ApiOperation(value = "删除商品表模块", notes = "删除商品表模块")
    public Result remove(@RequestBody List<String> ids) {
        return ResultUtil.success(productInfoService.deleteByIds(ids));
    }

}
