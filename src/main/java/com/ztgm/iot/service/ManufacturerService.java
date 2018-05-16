package com.ztgm.iot.service;

import com.ztgm.iot.pojo.ManuFacturer;

import java.util.List;
import java.util.Map;

public interface ManufacturerService {

    /**
     * 通过user bean对象查询user 列表
     * @param conditions 条件
     * @return 用户所属角色
     */
    List<ManuFacturer> selectManufacturerByConditions(Map conditions);


}
