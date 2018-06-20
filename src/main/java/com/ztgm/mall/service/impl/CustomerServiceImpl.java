package com.ztgm.mall.service.impl;

import com.ztgm.mall.dao.MUserMapper;
import com.ztgm.mall.pojo.MUser;
import com.ztgm.mall.service.CustomerService;
import com.ztgm.mall.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private MUserMapper mUserMapper;

    
    public MUser getUserByToken(String token) {
    	return mUserMapper.getUserByToken(token);
    }

    @Override
    public Map registCustomer(MUser customer) {
        Map rtn = new HashMap();
        rtn.put("code",0);

        if(null==customer){
            rtn.put("msg","customer对象为空");
            return rtn;
        }

        if(null==customer.getPhone() || "".equals(customer.getPhone())){
            rtn.put("msg","customer.phone对象为空");
            return rtn;
        }

        if(null==customer.getName() || "".equals(customer.getName())){
            rtn.put("msg","customer.name对象为空");
            return rtn;
        }

        //nickname
        if(0<mUserMapper.selectCountByPhone(customer.getPhone())){
            rtn.put("msg","注册用户手机号已经存在");
            return rtn;
        }else{
            customer.setId(UUIDUtil.getUUID());
            customer.setState(0);
            mUserMapper.insertSelective(customer);
            rtn.put("code",1);
            return rtn;
        }
    }


    @Override
    public MUser getCustomerByPhone(String phone) {
        return mUserMapper.selectByPhone(phone);
    }
}
