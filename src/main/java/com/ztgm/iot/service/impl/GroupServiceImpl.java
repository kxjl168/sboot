package com.ztgm.iot.service.impl;


import com.ztgm.iot.dao.GroupDeviceMapper;
import com.ztgm.iot.dao.GroupMapper;
import com.ztgm.iot.dao.GroupRegionMapper;
import com.ztgm.iot.dao.GroupUserMapper;
import com.ztgm.iot.pojo.*;
import com.ztgm.iot.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class GroupServiceImpl {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GroupMapper groupDao ;
    @Autowired
    private GroupUserMapper guserDao;
    @Autowired
    private GroupRegionMapper gregionDao;
    @Autowired
    private GroupDeviceMapper gdeviceDao;


    public Group findGroupById(String id) {

        try {
            Group w = groupDao.selectByPrimaryKey(id);
            return w;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询出错");
            return null;
        }
    }

    public List<Group> groupListByUserId(String userId) {

        try {
            List<Group> groups=groupDao.selectBUserId(userId);
            return groups;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询出错");
            return null;
        }
    }
    public List<Group> groupListByUserId2(String userId,String groupName) {

        try {
            List<Group> groups=groupDao.selectBUserId2(userId,groupName);
            return groups;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询出错");
            return null;
        }
    }
    public List<Group> groupList() {
        List<Group> deviceList = new ArrayList<>();
        try {

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询出错");
        }
        return deviceList;
    }

    //查询用户所有区域 (通过组查询)
    public List<Region> selectUserAllRegions(String userId){
        return  groupDao.selectUserAllRegions(userId);
    }

    //查询用户所有设备 (通过组查询)
    public List<Device> selectUserAllDevices(String userId){
        return  groupDao.selectUserAllDevices(userId);
    }

    //查询用户 除指定组外的 所有区域
    public List<Region> selectUserAllRegionsExGroup(String userId,  String groupId){
        return  groupDao.selectUserAllRegionsExGroup(userId,groupId);
    }

    //查询用户 除指定组外的 所有设备
    public List<Device> selectUserAllDevicesExGroup(String userId,  String groupId){
        return  groupDao.selectUserAllDevicesExGroup(userId,groupId);
    }

    //查询指定组的所有区域
    public List<Region> selectGroupRegions(String groupId){
        return  groupDao.selectGroupRegions(groupId);
    }

    //查询指定组的所有设备
    public List<Device> selectGroupDevices(String groupId){
        return  groupDao.selectGroupDevices(groupId);
    }

    //查询用户与网关相关联的组ID
    public List selectUserGatewayGroup(String userId){
        return groupDao.selectUserGatewayGroup(userId);
    }

    public int saveGroup(Group g) {

        int result = 0;
        try {
            result = groupDao.insertSelective(g);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("添加组失败");
        }
        return result;
    }

    public int updateGroup(Group g) {
        int result = 0;
        try {

            result =groupDao .updateByPrimaryKeySelective(g);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("更新组失败");
        }
        return result;
    }

    @Transactional
    public int deleteGroup(String groupId) {
        int result = 0;
        try {

            gdeviceDao.deleteByGroupId(groupId);
            gregionDao.deleteByGroupId(groupId);
            guserDao.deleteByGroupId(groupId);

            result = groupDao.deleteByPrimaryKey(groupId);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除组失败");
        }
        return result;
    }


    @Transactional
    public int saveGroupRegion(String groupId,List<String> regionIdList) {

        int result = 0;
        try {
            gregionDao.deleteByGroupId(groupId);
            GroupRegion gr=new GroupRegion();
            gr.setGroupId(groupId);
            for(String regionId : regionIdList){
                gr.setId(UUIDUtil.getUUID());
                gr.setRegionId(regionId);
                gregionDao.insert(gr);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("添加组区域失败");
        }
        return result;
    }

    @Transactional
    public int saveGroupDevice(String groupId,List<String> devIdList) {

        int result = 0;
        try {
            gdeviceDao.deleteByGroupId(groupId);
            GroupDevice gd=new GroupDevice();
            gd.setGroupId(groupId);
            for(String devId : devIdList){
                gd.setId(UUIDUtil.getUUID());
                gd.setDeviceId(devId);
                gdeviceDao.insert(gd);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("添加组设备失败");
        }
        return result;
    }

}
