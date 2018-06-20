package com.ztgm.mall.service;

import com.ztgm.mall.pojo.MUser;

import java.util.Map;

public interface CustomerService {

    Map registCustomer(MUser customer);

    MUser getCustomerByPhone(String phone);
    
    /**
     * 接口 token
     * @param token
     * @return
     * @author zj
     * @date 2018年6月20日
     */
    MUser getUserByToken(String token);
}
