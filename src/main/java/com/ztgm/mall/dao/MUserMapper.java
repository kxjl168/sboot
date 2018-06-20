package com.ztgm.mall.dao;

import com.ztgm.mall.pojo.MUser;

public interface MUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(MUser record);

    int insertSelective(MUser record);

    MUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MUser record);

    int updateByPrimaryKey(MUser record);

    int selectCountByPhone(String phone);

    MUser selectByPhone(String phone);
    
    
    /**
     * 接口 token
     * @param token
     * @return
     * @author zj
     * @date 2018年6月20日
     */
    MUser getUserByToken(String token);
}