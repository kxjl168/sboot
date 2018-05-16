package com.ztgm.iot.controller.WebController;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ztgm.iot.pojo.*;
import com.ztgm.iot.service.impl.GroupServiceImpl;
import com.ztgm.iot.service.impl.GroupUserServiceImpl;
import com.ztgm.iot.service.impl.UserServiceImpl;
import com.ztgm.iot.util.AppResult;
import com.ztgm.iot.util.AppResultUtil;
import com.ztgm.iot.util.UUIDUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/manager/group")
public class GroupController {

    @Autowired
    private GroupServiceImpl groupService;
    @Autowired
    private GroupUserServiceImpl groupUserService;
    @Autowired
    private UserServiceImpl userService;


    @RequestMapping("/etrGroupList")
    public String groupList(@RequestParam Map<String, String> params, Model model) {
        //pageNo
        String pn = params.get("pn");
        //pageSize;
        String ps = params.get("ps");
        String groupName=params.get("searchKey");

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

        List<Group> groups=null;
        if(groupName!=null){
            groups= groupService.groupListByUserId2(userId,groupName);
            model.addAttribute("searchKey", groupName);
        }else{
            groups= groupService.groupListByUserId(userId);
        }


        //分页信息
        PageInfo pageInfo = new PageInfo(groups);

        model.addAttribute("groups", groups);
        model.addAttribute("pager", pageInfo);


        // to do
        return "/frontend/group/group_list";
    }


    @RequestMapping("/addGroup")
    @ResponseBody
    public String addGroup(String groupName) {

        try{
            Subject subject = SecurityUtils.getSubject();
            Map principal = (Map) subject.getPrincipal();
            String userId = (String) principal.get("userId");

            Group g=new Group();
            g.setId(UUIDUtil.getUUID());
            g.setName(groupName);
            g.setCreateDate(new Date());
            List<Group> list=groupService.selectUserGatewayGroup(userId);
            if(list==null||list.size()==0){
                return "fail";
            }
            g.setParentId(String.valueOf(list.get(0)));
            groupService.saveGroup(g);

            GroupUser gu=new GroupUser();
            gu.setUserId(userId);
            gu.setGroupId(g.getId());
            gu.setType(1);
            gu.setId(UUIDUtil.getUUID());
            groupUserService.saveGroupUser(gu);

            return "success";
        }catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping("/updateGroupName")
    @ResponseBody
    public String updateGroupName(String groupId,String groupName) {

        try{
            Group g=groupService.findGroupById(groupId);
            g.setName(groupName);
            groupService.updateGroup(g);
            return "success";
        }catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }
    @RequestMapping("/delGroup")
    @ResponseBody
    public String delGroup(String groupId) {
        try{
            groupService.deleteGroup(groupId);

            return "success";
        }catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping("/etrGroupSetting")
    public String etrGroupSetting(String groupId ,Model model) {
        model.addAttribute("groupId", groupId);
        return "/frontend/group/group_setting";
    }



    @RequestMapping("/saveGroupRegion")
    @ResponseBody
    public String saveGroupRegion(String groupId,String regionIdList) {

        try{

            ObjectMapper ob=new ObjectMapper();
            List<String> regionIds=ob.readValue(regionIdList,List.class);

            groupService.saveGroupRegion(groupId,regionIds);

            return "success";
        }catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping("/saveGroupDevice")
    @ResponseBody
    public String saveGroupDevice(String groupId,String devIdList) {

        try{

            ObjectMapper ob=new ObjectMapper();
            List<String> regionIds=ob.readValue(devIdList,List.class);

            groupService.saveGroupDevice(groupId,regionIds);

            return "success";
        }catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    //查询用户所有区域 (通过组查询)
    @RequestMapping("/userAllRegions")
    @ResponseBody
    public  List<Region> selectUserAllRegions(String userId){

        return  groupService.selectUserAllRegions(userId);
    }

    //查询用户所有设备 (通过组查询)
    @RequestMapping("/userAllDevices")
    @ResponseBody
    public List<Device> selectUserAllDevices(String userId){
        return  groupService.selectUserAllDevices(userId);
    }

    //查询用户 除指定组外的 所有区域
    @RequestMapping("/userAllRegionsExGroup")
    @ResponseBody
    public List<Region> selectUserAllRegionsExGroup(  String groupId){

        Subject subject = SecurityUtils.getSubject();
        Map principal = (Map) subject.getPrincipal();
        String userId = (String) principal.get("userId");

        return  groupService.selectUserAllRegionsExGroup(userId,groupId);
    }

    //查询用户 除指定组外的 所有设备
    @RequestMapping("/userAllDevicesExGroup")
    @ResponseBody
    public List<Device> selectUserAllDevicesExGroup(String groupId){
        Subject subject = SecurityUtils.getSubject();
        Map principal = (Map) subject.getPrincipal();
        String userId = (String) principal.get("userId");
        return  groupService.selectUserAllDevicesExGroup(userId,groupId);
    }

    //查询指定组的所有区域
    @RequestMapping("/groupRegions")
    @ResponseBody
    public List<Region> selectGroupRegions(String groupId){
        return  groupService.selectGroupRegions(groupId);
    }

    //查询指定组的所有设备
    @RequestMapping("/groupDevices")
    @ResponseBody
    public List<Device> selectGroupDevices(String groupId){
        return  groupService.selectGroupDevices(groupId);
    }



}
