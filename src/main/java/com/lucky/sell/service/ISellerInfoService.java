package com.lucky.sell.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.sell.domain.SellerInfo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Ou Yang Xiao Guang
 * @since 2020-12-31
 */
public interface ISellerInfoService extends IService<SellerInfo> {

    /**
     * 通过openid查询卖家端信息
     *
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);
}

