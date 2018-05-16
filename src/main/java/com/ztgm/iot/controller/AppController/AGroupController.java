package com.ztgm.iot.controller.AppController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ztgm.iot.controller.AppController.parm.CodeParam;
import com.ztgm.iot.controller.AppController.parm.MessParam;
import com.ztgm.iot.pojo.Group;
import com.ztgm.iot.pojo.User;
import com.ztgm.iot.service.impl.GroupServiceImpl;
import com.ztgm.iot.service.impl.GroupUserServiceImpl;
import com.ztgm.iot.service.impl.UserServiceImpl;
import com.ztgm.iot.util.AppResult;
import com.ztgm.iot.util.AppResultUtil;
import com.ztgm.iot.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/interface/group")
public class AGroupController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private GroupServiceImpl groupService;
    @Autowired
    private GroupUserServiceImpl groupUserService;
    @Autowired
    private CodeParam appCode;
    @Autowired
    private MessParam appMess;


    @RequestMapping("/groupList")
    public AppResult groupList() {

        List<Group> gList = groupService.groupListByUserId(TokenUtil.getCurrentUser().getId());

        return AppResultUtil.success(gList);
    }


    @RequestMapping("/addMemberToGroups")
    public AppResult addMemberToGroups(String groupIdList, String phone) {
        if (groupIdList == null || "".equals(groupIdList)) {
            return AppResultUtil.fail();
        } else if (phone == null || "".equals(phone)) {
            return AppResultUtil.fail();
        }
        try {
            User u = userService.getUserByPhone(phone);
            if (u == null) {
                return AppResultUtil.fire(appCode.G001, appMess.G001, null);
            }
            ObjectMapper mapper = new ObjectMapper();
            List list = mapper.readValue(groupIdList, List.class);
            for (Object aList : list) {
                String groupId = String.valueOf(aList);
                groupUserService.addGroupMember(groupId, u.getId());
            }

            return AppResultUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AppResultUtil.fail();
        }
    }


}
