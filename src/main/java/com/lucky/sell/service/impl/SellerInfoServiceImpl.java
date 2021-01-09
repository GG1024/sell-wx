package com.lucky.sell.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.sell.domain.SellerInfo;
import com.lucky.sell.mapper.SellerInfoMapper;
import com.lucky.sell.service.ISellerInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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


    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        QueryWrapper<SellerInfo> where = new QueryWrapper<>();
        where.lambda().eq(SellerInfo::getOpenid, openid);
        return baseMapper.selectOne(where);
    }
}

