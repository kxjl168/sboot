package com.ztgm.iot.controller.AppController;

import com.ztgm.iot.controller.AppController.parm.CodeParam;
import com.ztgm.iot.controller.AppController.parm.MessParam;
import com.ztgm.iot.pojo.User;
import com.ztgm.iot.service.impl.GroupUserServiceImpl;
import com.ztgm.iot.service.impl.UserServiceImpl;
import com.ztgm.iot.util.AppResult;
import com.ztgm.iot.util.AppResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * app 用户组
 */
@RestController
@RequestMapping("/interface/group")
public class AGroupUserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private GroupUserServiceImpl groupUserService;
    @Autowired
    private CodeParam appCode;
    @Autowired
    private MessParam appMess;


    @RequestMapping("/memberList")
    public AppResult memberList(String groupId) {
        if (groupId == null || "".equals(groupId)) {
            return AppResultUtil.fail();
        }
        List<User> gList = groupUserService.findGroupMemberList(groupId);
        return AppResultUtil.success(gList);
    }

    @RequestMapping("/addMember")
    public AppResult addMember(String groupId, String phone) {
        if (groupId == null || "".equals(groupId)) {
            return AppResultUtil.fail();
        } else if (phone == null || "".equals(phone)) {
            return AppResultUtil.fail();
        }
        try {
            User u = userService.getUserByPhone(phone);
            if (u == null) {
//                return AppResultUtil.fire(AppResultUtil.no_user_code,AppResultUtil.no_user_message,null);
                return AppResultUtil.fire(appCode.G001, appMess.G001, null);
            }
            groupUserService.addGroupMember(groupId, u.getId());
            return AppResultUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AppResultUtil.fail();
        }
    }

    @RequestMapping("/delMember")
    public AppResult delMember(String groupId, String userId) {
        if (groupId == null || "".equals(groupId)) {
            return AppResultUtil.fail();
        } else if (userId == null || "".equals(userId)) {
            return AppResultUtil.fail();
        }
        try {
            if (groupId == null && userId == null) {
                return AppResultUtil.fail();
            }
            groupUserService.delGroupUser(groupId, userId);

            return AppResultUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AppResultUtil.fail();
        }
    }


}
