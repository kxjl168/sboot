package com.ztgm.iot.controller.WebController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ztgm.iot.pojo.GroupUser;
import com.ztgm.iot.pojo.User;
import com.ztgm.iot.service.impl.GroupUserServiceImpl;
import com.ztgm.iot.service.impl.UserServiceImpl;
import com.ztgm.iot.util.Message;
import com.ztgm.iot.util.UserIDUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manager/group")
public class GroupUserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private GroupUserServiceImpl groupUserService;


    @RequestMapping("/etrGroupUserList")
    public String showMemberList(@RequestParam Map<String, String> params, Model model) {
        //pageNo
        String pn = params.get("pn");
        //pageSize;
        String ps = params.get("ps");

        int pageNo = 0;
        int pageSize = 10;
        try {
            if (null != pn) {
                pageNo = Integer.parseInt(pn);
            }
            if (null != ps) {
                pageSize = Integer.parseInt(ps);
            }
        } catch (NumberFormatException e) {

        }

        PageHelper.startPage(pageNo, pageSize);

        Subject subject = SecurityUtils.getSubject();
        Map principal = (Map) subject.getPrincipal();
        String userId = (String) principal.get("userId");
        GroupUser groupUser = groupUserService.findGroupByUser(userId);
        List<User> groupList = new ArrayList<>();
        groupList = groupUserService.findGroupMemberList(groupUser.getGroupId());


//        List users = userService.selectUserByUser(params);
        //分页信息
        PageInfo pageInfo = new PageInfo(groupList);

        model.addAttribute("users", groupList);
        model.addAttribute("groupUser", groupUser);
        //map.put("pager",pageInfo);
        model.addAttribute("pager", pageInfo);

        return "/frontend/group/group_user_list";
    }

    @RequestMapping("/memberList")
    @ResponseBody
    public List<User> memberList(ModelMap map, HttpServletResponse response) {
        Subject subject = SecurityUtils.getSubject();
        String userId = UserIDUtil.getUserID(subject, response);
        GroupUser groupUser = groupUserService.findGroupByUser(userId);
        List<User> groupList = new ArrayList<>();
        if (groupUser.getType().equals(1)) {
            groupList = groupUserService.findGroupMemberList(groupUser.getGroupId());
        }
        return groupList;
    }

    @RequestMapping("/addMember")
    @ResponseBody
    public Message addMember(HttpServletRequest request) {
        Message msg = new Message();
        String phone = request.getParameter("phone");
        String groupId = request.getParameter("groupId");

        if (groupId == null) {
            Subject subject = SecurityUtils.getSubject();
            Map principal = (Map) subject.getPrincipal();
            String userId = (String) principal.get("userId");
            GroupUser groupUser = groupUserService.findGroupByUser(userId);
            groupId = groupUser.getGroupId();
        }


        try {
            User u = userService.getUserByPhone(phone);
            if (u == null) {
                msg.setBol(false);
                msg.setMessage("用户不存在!");
                return msg;
            }
            groupUserService.addGroupMember(groupId, u.getId());
            msg.setBol(true);
            return msg;
        } catch (Exception e) {
            e.printStackTrace();
            msg.setBol(false);
            msg.setMessage("操作失败!");
            return msg;
        }
    }

    @RequestMapping("/delMember")
    @ResponseBody
    public Message delMember(HttpServletRequest request) {
        Message msg = new Message();
        String userId = request.getParameter("userId");
        String groupId = request.getParameter("groupId");

        if (groupId == null) {
            Subject subject = SecurityUtils.getSubject();
            Map principal = (Map) subject.getPrincipal();
            GroupUser groupUser = groupUserService.findGroupByUser(userId);
            groupId = groupUser.getGroupId();
        }
        try {
            groupUserService.delGroupMember(groupId, userId);
            msg.setBol(true);
            return msg;
        } catch (Exception e) {
            e.printStackTrace();
            msg.setBol(false);
            msg.setMessage("操作失败!");
            return msg;
        }
    }


}
