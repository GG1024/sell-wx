package com.lucky.sell.service.impl;

import com.lucky.sell.domain.ProductCategory;
import com.lucky.sell.service.IProductCategoryService;
import com.lucky.sell.utils.JsonUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {
    @Autowired
    private IProductCategoryService iProductCategoryService;




    @Test
    public void findOne() {
        ProductCategory one = iProductCategoryService.findOne("1");
        System.out.println(JsonUtil.toJson(one));
    }

    @Test
    public void findAll() {
        List<ProductCategory> all = iProductCategoryService.findAll();
        System.out.println(JsonUtil.toJson(all));
    }

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        List<ProductCategory> byCategoryTypeIn = iProductCategoryService.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, byCategoryTypeIn.size());
        System.out.println(JsonUtil.toJson(byCategoryTypeIn));
    }

    @Test
    public void add() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("大爷最爱");
        productCategory.setCategoryType(1);
        iProductCategoryService.add(productCategory);
    }
}