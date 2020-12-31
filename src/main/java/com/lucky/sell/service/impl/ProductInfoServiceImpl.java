package com.lucky.sell.service.impl;

import com.lucky.sell.domain.ProductInfo;
import com.lucky.sell.mapper.ProductInfoMapper;
import com.lucky.sell.service.IProductInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author Ou Yang Xiao Guang
 * @since 2020-12-31
 */
@Service
@Slf4j
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoMapper, ProductInfo> implements IProductInfoService {


    /**
     * 查询商品表模块
     *
     * @param id 商品表模块ID
     * @return 商品表模块
     */
    @Override
    public ProductInfo selectById(String id) {
        return baseMapper.selectById(id);
    }

    /**
     * 查询商品表模块列表
     *
     * @param productInfo 商品表模块
     * @return 商品表模块
     */
    @Override
    public List<ProductInfo> selectList(ProductInfo productInfo) {
        QueryWrapper<ProductInfo> where = new QueryWrapper<>();
        where.setEntity(productInfo);
        return baseMapper.selectList(where);
    }

    /**
     * 新增商品表模块
     *
     * @param productInfo 商品表模块
     * @return 结果
     */
    @Override
    public int insert(ProductInfo productInfo) {
        return baseMapper.insert(productInfo);
    }

    /**
     * 修改商品表模块
     *
     * @param productInfo 商品表模块
     * @return 结果
     */
    @Override
    public int update(ProductInfo productInfo) {
        return baseMapper.updateById(productInfo);
    }

    /**
     * 批量删除商品表模块
     *
     * @param ids 需要删除的商品表模块ID
     * @return 结果
     */
    @Override
    public int deleteByIds(List<String> ids) {
        return baseMapper.deleteBatchIds(ids);
    }

    /**
     * 删除商品表模块信息
     *
     * @param id 商品表模块ID
     * @return 结果
     */
    @Override
    public int deleteById(String id) {
        return baseMapper.deleteById(id);
    }


}

