package com.ztgm.iot.service.impl;

import com.ztgm.iot.dao.ManuFacturerMapper;
import com.ztgm.iot.pojo.ManuFacturer;
import com.ztgm.iot.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    @Autowired
    private ManuFacturerMapper manuFacturerMapper;


    @Override
    public List<ManuFacturer> selectManufacturerByConditions(Map conditions) {
        return manuFacturerMapper.selectManufacturerByConditions(conditions);
    }
}
