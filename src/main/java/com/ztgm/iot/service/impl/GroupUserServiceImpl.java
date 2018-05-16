package com.ztgm.iot.service.impl;


import com.ztgm.iot.dao.GroupMapper;
import com.ztgm.iot.dao.GroupUserMapper;
import com.ztgm.iot.pojo.Group;
import com.ztgm.iot.pojo.GroupUser;
import com.ztgm.iot.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Transactional
@Service
public class GroupUserServiceImpl {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private GroupUserMapper groupUserDao;
    @Autowired
    private GroupMapper groupMapper;


    public GroupUser findGroupByUser(String userId){
        GroupUser gu=null;
        List<GroupUser> list=groupUserDao.findGroupByUser(userId);
        if (list!=null&&list.size()>0){
            gu=list.get(0);
        }
        return gu;
    }

    public List<User> findGroupMemberList(String groupId){
        Group group = groupMapper.selectByPrimaryKey(groupId);
        String groupids = group.getParentId()==null?groupId:group.getParentId();
        return groupUserDao.selectMemberInGroup(groupids);
    }

    public void addGroupMember(String groupId,String userId) {

        try {
            GroupUser groupUser=new GroupUser();
            groupUser.setGroupId(groupId);
            groupUser.setUserId(userId);
            groupUser.setId(UUID.randomUUID().toString());
            groupUser.setType(0);
            groupUserDao.insert(groupUser);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("添加组员失败");
        }
    }
    public void saveGroupUser(GroupUser gu) {

        try {
            groupUserDao.insert(gu);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("添加组员失败");
        }
    }

    public void delGroupMember(String groupId,String userId) {

        try {

            groupUserDao.deleteGroupMember(groupId,userId);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除组员失败");
        }
    }

    public void delGroupUser(String groupId,String userId) {

        try {

            groupUserDao.deleteGroupUser(groupId,userId);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除组员失败");
        }
    }

}
