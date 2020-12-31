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

    /**
     * 查询类目表模块列表
     */
    /*@GetMapping("/list")
    @ApiOperation(value = "查询类目表模块列表", notes = "查询类目表模块列表")
    public TableDataInfo list(ProductCategory productCategory) {
        startPage();
        List<ProductCategory> list = productCategoryService.selectList(productCategory);
        return getDataTable(list);
    }*/


    /**
     * 获取类目表模块详细信息
     */
    @GetMapping(value = "getInfo/{id}")
    @ApiOperation(value = "获取类目表模块详细信息", notes = "获取类目表模块详细信息")
    public Result getInfo(@PathVariable("id") String id) {
        return ResultUtil.success(productCategoryService.selectById(id));
    }

    /**
     * 新增类目表模块
     */
    @PostMapping("add")
    @ApiOperation(value = "新增类目表模块", notes = "新增类目表模块")
    public Result add(@RequestBody ProductCategory productCategory) {
        return ResultUtil.success(productCategoryService.insert(productCategory));
    }

    /**
     * 修改类目表模块
     */
    @PutMapping("edit")
    @ApiOperation(value = " 修改类目表模块", notes = "修改类目表模块")
    public Result edit(@RequestBody ProductCategory productCategory) {
        return ResultUtil.success(productCategoryService.update(productCategory));
    }

    /**
     * 删除类目表模块
     */
    @DeleteMapping("remove")
    @ApiOperation(value = "删除类目表模块", notes = "删除类目表模块")
    public Result remove(@RequestBody List<String> ids) {
        return ResultUtil.success(productCategoryService.deleteByIds(ids));
    }

}
