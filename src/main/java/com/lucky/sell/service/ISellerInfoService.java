package com.lucky.sell.service;

import com.lucky.sell.domain.SellerInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Ou Yang Xiao Guang
 * @since 2020-12-31
 */
public interface ISellerInfoService extends IService<SellerInfo> {

    /**
     * 查询模块
     *
     * @param id 模块ID
     * @return 模块
     */
    SellerInfo selectById(String id);

    /**
     * 查询模块列表
     *
     * @param sellerInfo 模块
     * @return 模块集合
     */
    List<SellerInfo> selectList(SellerInfo sellerInfo);

    /**
     * 新增模块
     *
     * @param sellerInfo 模块
     * @return 结果
     */
    int insert(SellerInfo sellerInfo);

    /**
     * 修改模块
     *
     * @param sellerInfo 模块
     * @return 结果
     */
    int update(SellerInfo sellerInfo);

    /**
     * 批量删除模块
     *
     * @param ids 需要删除的模块ID
     * @return 结果
     */
    int deleteByIds(List<String> ids);

    /**
     * 删除模块信息
     *
     * @param id 模块ID
     * @return 结果
     */
    int deleteById(String id);


}

