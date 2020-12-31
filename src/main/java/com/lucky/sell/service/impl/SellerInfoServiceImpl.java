package com.lucky.sell.service.impl;

import com.lucky.sell.domain.SellerInfo;
import com.lucky.sell.mapper.SellerInfoMapper;
import com.lucky.sell.service.ISellerInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Ou Yang Xiao Guang
 * @since 2020-12-31
 */
@Service
@Slf4j
public class SellerInfoServiceImpl extends ServiceImpl<SellerInfoMapper, SellerInfo> implements ISellerInfoService {


    /**
     * 查询模块
     *
     * @param id 模块ID
     * @return 模块
     */
    @Override
    public SellerInfo selectById(String id) {
        return baseMapper.selectById(id);
    }

    /**
     * 查询模块列表
     *
     * @param sellerInfo 模块
     * @return 模块
     */
    @Override
    public List<SellerInfo> selectList(SellerInfo sellerInfo) {
        QueryWrapper<SellerInfo> where = new QueryWrapper<>();
        where.setEntity(sellerInfo);
        return baseMapper.selectList(where);
    }

    /**
     * 新增模块
     *
     * @param sellerInfo 模块
     * @return 结果
     */
    @Override
    public int insert(SellerInfo sellerInfo) {
        return baseMapper.insert(sellerInfo);
    }

    /**
     * 修改模块
     *
     * @param sellerInfo 模块
     * @return 结果
     */
    @Override
    public int update(SellerInfo sellerInfo) {
        return baseMapper.updateById(sellerInfo);
    }

    /**
     * 批量删除模块
     *
     * @param ids 需要删除的模块ID
     * @return 结果
     */
    @Override
    public int deleteByIds(List<String> ids) {
        return baseMapper.deleteBatchIds(ids);
    }

    /**
     * 删除模块信息
     *
     * @param id 模块ID
     * @return 结果
     */
    @Override
    public int deleteById(String id) {
        return baseMapper.deleteById(id);
    }


}

