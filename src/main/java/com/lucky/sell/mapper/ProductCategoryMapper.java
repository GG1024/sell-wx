package com.lucky.sell.mapper;

import com.lucky.sell.domain.ProductCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 类目表 Mapper 接口
 * </p>
 *
 * @author Ou Yang Xiao Guang
 * @since 2020-12-31
 */
@Mapper
public interface ProductCategoryMapper extends BaseMapper<ProductCategory> {

   List<ProductCategory> selectByCategoryType(Integer categoryType);

}
