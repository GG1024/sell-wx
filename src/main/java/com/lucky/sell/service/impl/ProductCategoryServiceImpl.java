package com.lucky.sell.service.impl;

import com.lucky.sell.domain.ProductCategory;
import com.lucky.sell.mapper.ProductCategoryMapper;
import com.lucky.sell.service.IProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.List;

/**
 * <p>
 * 类目表 服务实现类
 * </p>
 *
 * @author Ou Yang Xiao Guang
 * @since 2020-12-31
 */
@Service
@Slf4j
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements IProductCategoryService {

    @Override
    public ProductCategory findOne(String categoryId) {
        QueryWrapper<ProductCategory> where = new QueryWrapper<>();
        where.lambda().eq(ProductCategory::getCategoryId,categoryId);
        return baseMapper.selectById(where);
    }

    @Override
    public List<ProductCategory> findAll() {
        return baseMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> cateforyTyprList) {
        QueryWrapper<ProductCategory> where = new QueryWrapper<>();
        where.lambda().in(ProductCategory::getCategoryType, cateforyTyprList);
        return baseMapper.selectList(where);
    }

    @Override
    public ProductCategory add(ProductCategory productCategory) {
        return baseMapper.insert(productCategory) > 0 ? productCategory : null;
    }
}

