package com.ztgm.mall.dao;

import java.util.List;

import com.ztgm.mall.pojo.UserComplaint;
import com.ztgm.mall.pojo.UserComplaint;

public interface UserComplaintMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserComplaint record);

    int insertSelective(UserComplaint record);

    UserComplaint selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserComplaint record);

    int updateByPrimaryKey(UserComplaint record);
    
	/**
   	 * 条件查询
   	 * 
   	 * @param role
   	 * @return
   	 * @author zj
   	 * @date 2018年5月9日
   	 */
   	List<UserComplaint> selectUserComplaintList(UserComplaint role);
}