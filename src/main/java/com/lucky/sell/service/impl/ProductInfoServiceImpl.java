package com.lucky.sell.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.sell.config.UpYunConfig;
import com.lucky.sell.domain.ProductInfo;
import com.lucky.sell.domain.dto.CartDTO;
import com.lucky.sell.enums.ProductStatusEnum;
import com.lucky.sell.enums.ResultEnum;
import com.lucky.sell.exception.SellException;
import com.lucky.sell.mapper.ProductInfoMapper;
import com.lucky.sell.service.IProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    private UpYunConfig upYunConfig;

    @Override
    public int onSale(String productId) {

        /**
         * 查询商品
         * 判断商品状态是否为上架
         */
        QueryWrapper<ProductInfo> where = new QueryWrapper<>();
        where.lambda().eq(ProductInfo::getProductId, productId);
        ProductInfo productInfo = baseMapper.selectOne(where);
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.UP) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return update(productInfo);
    }

    @Override
    public int offSale(String productId) {
        /**
         * 查询商品
         * 判断商品状态是否为下架
         */
        QueryWrapper<ProductInfo> where = new QueryWrapper<>();
        where.lambda().eq(ProductInfo::getProductId, productId);
        ProductInfo productInfo = baseMapper.selectOne(where);
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return update(productInfo);
    }

    @Override
    public ProductInfo findOne(String productId) {
        QueryWrapper<ProductInfo> where = new QueryWrapper<>();
        where.lambda().eq(ProductInfo::getProductId, productId);
        Optional<ProductInfo> optionalProductInfo = Optional.ofNullable(baseMapper.selectOne(where));
        optionalProductInfo.ifPresent(e -> e.addImageHost(upYunConfig.getImageHost()));
        return optionalProductInfo.orElse(null);
    }


    @Override
    public List<ProductInfo> findUpAll() {
        QueryWrapper<ProductInfo> where = new QueryWrapper<>();
        where.lambda().eq(ProductInfo::getProductStatus, ProductStatusEnum.UP.getCode());
        return baseMapper.selectList(where).stream().map(e -> e.addImageHost(upYunConfig.getImageHost())).collect(Collectors.toList());
    }

    @Override
    public Page<ProductInfo> findAll(Integer page, Integer size) {
        IPage<ProductInfo> productInfoIPage = baseMapper.selectPage(new Page<>(page, size), new QueryWrapper<>());

        Page resultPage = new Page();
        resultPage.setCurrent(page);
        resultPage.setSize(size);
        resultPage.setTotal(productInfoIPage.getTotal());
        resultPage.setRecords(productInfoIPage.getRecords());
        return resultPage;
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {

        for (CartDTO cartDTO : cartDTOList) {
            QueryWrapper<ProductInfo> where = new QueryWrapper<>();
            where.lambda().eq(ProductInfo::getProductId, cartDTO.getProductId());

            ProductInfo productInfo = baseMapper.selectOne(where);
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);

            update(productInfo);
        }
    }

    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            QueryWrapper<ProductInfo> where = new QueryWrapper<>();
            where.lambda().eq(ProductInfo::getProductId, cartDTO.getProductId());
            ProductInfo productInfo = baseMapper.selectOne(where);
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            update(productInfo);
        }
    }


    private int update(ProductInfo productInfo) {
        QueryWrapper<ProductInfo> where = new QueryWrapper<>();
        where.lambda().eq(ProductInfo::getProductId, productInfo.getProductId());
        return baseMapper.update(productInfo, where);
    }
}

