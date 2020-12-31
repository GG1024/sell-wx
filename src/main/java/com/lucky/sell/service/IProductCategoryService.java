package com.lucky.sell.service;

import com.lucky.sell.domain.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 类目表 服务类
 * </p>
 *
 * @author Ou Yang Xiao Guang
 * @since 2020-12-31
 */
public interface IProductCategoryService extends IService<ProductCategory> {

    /**
     * 查询类目表模块
     *
     * @param id 类目表模块ID
     * @return 类目表模块
     */
    ProductCategory selectById(String id);

    /**
     * 查询类目表模块列表
     *
     * @param productCategory 类目表模块
     * @return 类目表模块集合
     */
    List<ProductCategory> selectList(ProductCategory productCategory);

    /**
     * 新增类目表模块
     *
     * @param productCategory 类目表模块
     * @return 结果
     */
    int insert(ProductCategory productCategory);

    /**
     * 修改类目表模块
     *
     * @param productCategory 类目表模块
     * @return 结果
     */
    int update(ProductCategory productCategory);

    /**
     * 批量删除类目表模块
     *
     * @param ids 需要删除的类目表模块ID
     * @return 结果
     */
    int deleteByIds(List<String> ids);

    /**
     * 删除类目表模块信息
     *
     * @param id 类目表模块ID
     * @return 结果
     */
    int deleteById(String id);


}

