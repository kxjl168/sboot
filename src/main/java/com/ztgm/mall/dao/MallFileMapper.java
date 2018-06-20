package com.ztgm.mall.dao;

import com.ztgm.mall.pojo.MallFile;

public interface MallFileMapper {
    int deleteByPrimaryKey(String id);

    int insert(MallFile record);

    int insertSelective(MallFile record);

    MallFile selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MallFile record);

    int updateByPrimaryKey(MallFile record);
}