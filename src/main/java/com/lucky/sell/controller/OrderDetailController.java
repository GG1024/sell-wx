package com.lucky.sell.controller;

import com.lucky.sell.domain.OrderDetail;
import com.lucky.sell.service.IOrderDetailService;
import com.lucky.sell.utils.ResultUtil;
import com.lucky.sell.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 * 订单详情表 前端控制器
 * </p>
 *
 * @author Ou Yang Xiao Guang
 * @since 2020-12-31
 */
@Api(tags = {"订单详情表"})
@RestController
@RequestMapping("/order-detail")
public class OrderDetailController {

    @Resource
    private IOrderDetailService orderDetailService;

//    /**
//     * 查询订单详情表模块列表
//     */
//    @GetMapping("/list")
//    @ApiOperation(value = "查询订单详情表模块列表", notes = "查询订单详情表模块列表")
//    public TableDataInfo list(OrderDetail orderDetail) {
//        startPage();
//        List<OrderDetail> list = orderDetailService.selectList(orderDetail);
//        return getDataTable(list);
//    }



    /**
     * 获取订单详情表模块详细信息
     */
    @GetMapping(value = "getInfo/{id}")
    @ApiOperation(value = "获取订单详情表模块详细信息", notes = "获取订单详情表模块详细信息")
    public Result getInfo(@PathVariable("id") String id) {
        return ResultUtil.success(orderDetailService.selectById(id));
    }

    /**
     * 新增订单详情表模块
     */
    @PostMapping("add")
    @ApiOperation(value = "新增订单详情表模块", notes = "新增订单详情表模块")
    public Result add(@RequestBody OrderDetail orderDetail) {
        return ResultUtil.success(orderDetailService.insert(orderDetail));
    }

    /**
     * 修改订单详情表模块
     */
    @PutMapping("edit")
    @ApiOperation(value = " 修改订单详情表模块", notes = "修改订单详情表模块")
    public Result edit(@RequestBody OrderDetail orderDetail) {
        return ResultUtil.success(orderDetailService.update(orderDetail));
    }

    /**
     * 删除订单详情表模块
     */
    @DeleteMapping("remove")
    @ApiOperation(value = "删除订单详情表模块", notes = "删除订单详情表模块")
    public Result remove(@RequestBody List<String> ids) {
        return ResultUtil.success(orderDetailService.deleteByIds(ids));
    }

}
