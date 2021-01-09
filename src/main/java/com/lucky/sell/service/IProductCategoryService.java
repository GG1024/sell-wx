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

    ProductCategory findOne(String categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> cateforyTyprList);

    ProductCategory add(ProductCategory productCategory);


}

