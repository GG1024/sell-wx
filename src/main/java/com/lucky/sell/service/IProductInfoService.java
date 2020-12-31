package com.lucky.sell.service;

import com.lucky.sell.domain.ProductInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author Ou Yang Xiao Guang
 * @since 2020-12-31
 */
public interface IProductInfoService extends IService<ProductInfo> {

    /**
     * 查询商品表模块
     *
     * @param id 商品表模块ID
     * @return 商品表模块
     */
    ProductInfo selectById(String id);

    /**
     * 查询商品表模块列表
     *
     * @param productInfo 商品表模块
     * @return 商品表模块集合
     */
    List<ProductInfo> selectList(ProductInfo productInfo);

    /**
     * 新增商品表模块
     *
     * @param productInfo 商品表模块
     * @return 结果
     */
    int insert(ProductInfo productInfo);

    /**
     * 修改商品表模块
     *
     * @param productInfo 商品表模块
     * @return 结果
     */
    int update(ProductInfo productInfo);

    /**
     * 批量删除商品表模块
     *
     * @param ids 需要删除的商品表模块ID
     * @return 结果
     */
    int deleteByIds(List<String> ids);

    /**
     * 删除商品表模块信息
     *
     * @param id 商品表模块ID
     * @return 结果
     */
    int deleteById(String id);


}

