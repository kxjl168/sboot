package com.ztgm.iot.dao;

import com.ztgm.iot.pojo.ManuFacturer;

import java.util.List;
import java.util.Map;

public interface ManuFacturerMapper {
    int deleteByPrimaryKey(String id);

    int insert(ManuFacturer record);

    int insertSelective(ManuFacturer record);

    ManuFacturer selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ManuFacturer record);

    int updateByPrimaryKey(ManuFacturer record);


    /**
     * 通过user bean对象查询user 列表
     * @param conditions 条件
     * @return 用户所属角色
     */
    List<ManuFacturer> selectManufacturerByConditions(Map conditions);
}