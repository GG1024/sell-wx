package com.lucky.sell.controller;

import com.lucky.sell.domain.SellerInfo;
import com.lucky.sell.service.ISellerInfoService;
import com.lucky.sell.utils.ResultUtil;
import com.lucky.sell.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Ou Yang Xiao Guang
 * @since 2020-12-31
 */
@Api(tags = {""})
@RestController
@RequestMapping("/seller-info")
public class SellerInfoController {

    @Resource
    private ISellerInfoService sellerInfoService;

    /**
     * 查询模块列表
     */
/*    @GetMapping("/list")
    @ApiOperation(value = "查询模块列表", notes = "查询模块列表")
    public TableDataInfo list(SellerInfo sellerInfo) {
        startPage();
        List<SellerInfo> list = sellerInfoService.selectList(sellerInfo);
        return getDataTable(list);
    }*/


    /**
     * 获取模块详细信息
     */
    @GetMapping(value = "getInfo/{id}")
    @ApiOperation(value = "获取模块详细信息", notes = "获取模块详细信息")
    public Result getInfo(@PathVariable("id") String id) {
        return ResultUtil.success(sellerInfoService.selectById(id));
    }

    /**
     * 新增模块
     */
    @PostMapping("add")
    @ApiOperation(value = "新增模块", notes = "新增模块")
    public Result add(@RequestBody SellerInfo sellerInfo) {
        return ResultUtil.success(sellerInfoService.insert(sellerInfo));
    }

    /**
     * 修改模块
     */
    @PutMapping("edit")
    @ApiOperation(value = " 修改模块", notes = "修改模块")
    public Result edit(@RequestBody SellerInfo sellerInfo) {
        return ResultUtil.success(sellerInfoService.update(sellerInfo));
    }

    /**
     * 删除模块
     */
    @DeleteMapping("remove")
    @ApiOperation(value = "删除模块", notes = "删除模块")
    public Result remove(@RequestBody List<String> ids) {
        return ResultUtil.success(sellerInfoService.deleteByIds(ids));
    }

}
