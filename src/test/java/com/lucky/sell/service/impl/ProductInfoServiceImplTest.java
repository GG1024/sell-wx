package com.lucky.sell.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.sell.domain.ProductInfo;
import com.lucky.sell.enums.ProductStatusEnum;
import com.lucky.sell.service.IProductInfoService;
import com.lucky.sell.utils.JsonUtil;
import com.lucky.sell.utils.KeyUtil;
import com.lucky.sell.utils.MathUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {
    @Autowired
    private IProductInfoService iProductInfoService;

    @Test
    public void add() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(KeyUtil.genUniqueKey());
        productInfo.setProductName("娃娃菜炖豆腐");
        productInfo.setProductPrice(new BigDecimal(8));
        productInfo.setProductStock(200);
        productInfo.setProductDesc("大人小孩都爱喝");
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfo.setCategoryType(0);
        productInfo.setProductIcon("http://static.galileo.jpg");
        iProductInfoService.save(productInfo);
    }

    @Test
    public void onSale() {
        iProductInfoService.onSale("1609739127085951147");
    }

    @Test
    public void offSale() {
        iProductInfoService.offSale("1609739127085951147");
    }

    @Test
    public void findOne() {
        ProductInfo one = iProductInfoService.findOne("1609739127085951147");
        System.out.println(JsonUtil.toJson(one));
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> upAll = iProductInfoService.findUpAll();

        System.out.println(JsonUtil.toJson(upAll));
    }

    @Test
    public void findAll() {
        Page<ProductInfo> all = iProductInfoService.findAll(0, 2);
        System.out.println(JsonUtil.toJson(all));
    }

    @Test
    public void increaseStock() {
    }

    @Test
    public void decreaseStock() {
    }
}