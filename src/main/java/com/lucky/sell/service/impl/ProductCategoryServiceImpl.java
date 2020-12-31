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


    /**
     * 查询类目表模块
     *
     * @param id 类目表模块ID
     * @return 类目表模块
     */
    @Override
    public ProductCategory selectById(String id) {
        return baseMapper.selectById(id);
    }

    /**
     * 查询类目表模块列表
     *
     * @param productCategory 类目表模块
     * @return 类目表模块
     */
    @Override
    public List<ProductCategory> selectList(ProductCategory productCategory) {
        QueryWrapper<ProductCategory> where = new QueryWrapper<>();
        where.setEntity(productCategory);
        return baseMapper.selectList(where);
    }

    /**
     * 新增类目表模块
     *
     * @param productCategory 类目表模块
     * @return 结果
     */
    @Override
    public int insert(ProductCategory productCategory) {
        return baseMapper.insert(productCategory);
    }

    /**
     * 修改类目表模块
     *
     * @param productCategory 类目表模块
     * @return 结果
     */
    @Override
    public int update(ProductCategory productCategory) {
        return baseMapper.updateById(productCategory);
    }

    /**
     * 批量删除类目表模块
     *
     * @param ids 需要删除的类目表模块ID
     * @return 结果
     */
    @Override
    public int deleteByIds(List<String> ids) {
        return baseMapper.deleteBatchIds(ids);
    }

    /**
     * 删除类目表模块信息
     *
     * @param id 类目表模块ID
     * @return 结果
     */
    @Override
    public int deleteById(String id) {
        return baseMapper.deleteById(id);
    }


}

