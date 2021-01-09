package com.lucky.sell.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.sell.domain.ProductInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.sell.domain.dto.CartDTO;

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
     * 商品上架
     * @param productId
     * @return
     */
    int onSale(String productId);

    /**
     * 商品下架
     * @param productId
     * @return
     */
    int offSale(String productId);

    ProductInfo findOne(String productId);

    /** 查询所有上架的商品**/
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Integer page, Integer size);
    /** 加库存**/
    void increaseStock(List<CartDTO> cartDTOList);
    /** 减库存**/
    void decreaseStock(List<CartDTO> cartDTOList);

}

