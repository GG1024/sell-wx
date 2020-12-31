package com.lucky.sell.controller;

import com.lucky.sell.domain.OrderMaster;
import com.lucky.sell.service.IOrderMasterService;
import com.lucky.sell.utils.ResultUtil;
import com.lucky.sell.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 * 订单主表 前端控制器
 * </p>
 *
 * @author Ou Yang Xiao Guang
 * @since 2020-12-31
 */
@Api(tags = {"订单主表"})
@RestController
@RequestMapping("/order-master")
public class OrderMasterController {

    @Resource
    private IOrderMasterService orderMasterService;
    /**
     * 查询订单主表模块列表
     */
      /*@GetMapping("/list")
      @ApiOperation(value = "查询订单主表模块列表",notes = "查询订单主表模块列表")
      public TableDataInfo list(OrderMaster orderMaster)
     {
            startPage();
            List<OrderMaster> list = orderMasterService.selectList(orderMaster);
            return getDataTable(list);
      }*/


    /**
     * 获取订单主表模块详细信息
     */
    @GetMapping(value = "getInfo/{id}")
    @ApiOperation(value = "获取订单主表模块详细信息", notes = "获取订单主表模块详细信息")
    public Result getInfo(@PathVariable("id") String id) {
        return ResultUtil.success(orderMasterService.selectById(id));
    }

    /**
     * 新增订单主表模块
     */
    @PostMapping("add")
    @ApiOperation(value = "新增订单主表模块", notes = "新增订单主表模块")
    public Result add(@RequestBody OrderMaster orderMaster) {
        return ResultUtil.success(orderMasterService.insert(orderMaster));
    }

    /**
     * 修改订单主表模块
     */
    @PutMapping("edit")
    @ApiOperation(value = " 修改订单主表模块", notes = "修改订单主表模块")
    public Result edit(@RequestBody OrderMaster orderMaster) {
        return ResultUtil.success(orderMasterService.update(orderMaster));
    }

    /**
     * 删除订单主表模块
     */
    @DeleteMapping("remove")
    @ApiOperation(value = "删除订单主表模块", notes = "删除订单主表模块")
    public Result remove(@RequestBody List<String> ids) {
        return ResultUtil.success(orderMasterService.deleteByIds(ids));
    }

}
