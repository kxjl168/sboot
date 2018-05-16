package com.ztgm.iot.controller.WebController;

import com.ztgm.iot.pojo.Notice;
import com.ztgm.iot.pojo.User;
import com.ztgm.iot.service.RoleService;
import com.ztgm.iot.service.UserService;
import com.ztgm.iot.service.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manager")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private WelcomeService welcomeService;


    @RequestMapping("/user/index")
    public String index(Map<String, Object> map) {
        map.put("name", "admin");
        map.put("admin", "admin2");
        return "/frontend/user/welcome";
    }

    //登录后  首页
    @RequestMapping("/admin/index")
    public String admin(Map<String, Object> map, HttpServletRequest request) {
        Map principal = (Map) request.getAttribute("principal");
        String userId = (String) principal.get("userId");
        List<String> groupIdList = welcomeService.getGroupIdByUserId(userId);
        Map hrefCount = welcomeService.getHrefCount(groupIdList);
        Map selectYearListMap = welcomeService.getSelectYearList(groupIdList);
        map.put("hrefCount", hrefCount);
        map.put("selectYearListMap", selectYearListMap);
//        if(((Map)principal.get("roles")).get("sys_role_id")
        boolean isAdmin = false;
        List<Map> roleList = (List<Map>) principal.get("roles");
        for (Map role : roleList) {
            if (role.get("sys_role_id").equals("1")) {   // 系统管理员
                isAdmin = true;
                break;
            }
        }
//        if (isAdmin) {
//            return "redirect:/manager/user/manager.action";
//        }
        return "/backend/admin/welcome(new)";
    }

    @RequestMapping("/user/getNoticeList")
    @ResponseBody
    public List<Notice> getNoticeList(HttpServletRequest request) {
        Map principal = (Map) request.getAttribute("principal");
        String userId = (String) principal.get("userId");
        List<Notice> noticeList = welcomeService.getUserNotice(userId);
        return noticeList;
    }

    @RequestMapping("/user/updateNoticeRead")
    @ResponseBody
    public void updateNoticeRead(Notice notice) {
        String noticeId = notice.getId();
        welcomeService.updateNoticeRead(noticeId);
    }

    @RequestMapping("/user/getRegionLogCountList")
    @ResponseBody
    public List<Map> getRegionLogCountList(HttpServletRequest request) {
        String year = request.getParameter("year");
        if (year == null) {
            Calendar calendar = Calendar.getInstance();
            year = String.valueOf(calendar.get(Calendar.YEAR));
        }
        List<String> groupIdList = getGroupIdList(request);
        List<Map> regionLogCountList = welcomeService.getRegionLogCountList(groupIdList, year);
        return regionLogCountList;
    }

    @RequestMapping("/user/getSceneLogCountList")
    @ResponseBody
    public List<Map> getSceneLogCountList(HttpServletRequest request) {
        String year = request.getParameter("year");
        if (year == null) {
            Calendar calendar = Calendar.getInstance();
            year = String.valueOf(calendar.get(Calendar.YEAR));
        }
        List<String> groupIdList = getGroupIdList(request);
        List<Map> regionLogCountList = welcomeService.getSceneLogCountList(groupIdList, year);
        return regionLogCountList;
    }

    @RequestMapping("/user/getDeviceStatusCountList")
    @ResponseBody
    public Map getDeviceStatusCountList(HttpServletRequest request) {
        List<String> groupIdList = getGroupIdList(request);
        Map deviceStatusCountMap = welcomeService.getDeviceStatusCountList(groupIdList);
        return deviceStatusCountMap;
    }

    /**
     * 获得用户所在组的id集合
     *
     * @param request
     * @return
     */
    private List<String> getGroupIdList(HttpServletRequest request) {
        Map principal = (Map) request.getAttribute("principal");
        String userId = (String) principal.get("userId");
        List<String> groupIdList = welcomeService.getGroupIdByUserId(userId);
        return groupIdList;
    }


    @RequestMapping("/admin/registerAdminPage")
    public String registerAdminPage() {
        //如果系统中已经存在系统管理员用户，则跳转到登录页面
        //List adminUsers = userService.getAdminUsers();
        List adminUsers = null;
        if (null == adminUsers || 0 == adminUsers.size()) {
            return "/backend/admin/registerAdminPage";
        } else {
            return "redirect:/logout.action";
        }
    }


    @RequestMapping("/admin/registerSubmit")
    public String registerSubmit(HttpServletRequest request, HttpServletResponse response) {
        //如果系统中已经存在系统管理员用户，则跳转到登录页面
        List adminUsers = userService.getAdminUsers();
        //List adminUsers = null;
        if (!(null == adminUsers || 0 == adminUsers.size())) {
            return "redirect:/login.action";
        }


        HttpSession session = request.getSession();
        String userCaptchaCode = request.getParameter("captchaCode");
        String crctValidateCode = (String) session.getAttribute("validateCode");

        /*if(!(null!=userCaptchaCode && null!=crctValidateCode && crctValidateCode.equalsIgnoreCase(userCaptchaCode))){
            request.setAttribute("errMsg","验证码不正确");
            return registerAdminPage();
        }*/

        //读取参数
        User user = new User();
        String userName = request.getParameter("account");
        if (null == userName || "".equalsIgnoreCase(userName)) {
            request.setAttribute("errMsg", "用户名为空");
            return registerAdminPage();
        }
        user.setUsername(userName);

        String telephone = request.getParameter("cellphone");
        if (null == telephone || "".equalsIgnoreCase(telephone)) {
            request.setAttribute("errMsg", "手机号为空");
            return registerAdminPage();
        }
        user.setTelephone(telephone);

        String password = request.getParameter("password");
        if (null == password || "".equalsIgnoreCase(password.trim())) {
            request.setAttribute("errMsg", "密码为空");
            return registerAdminPage();
        }
        String confirm = request.getParameter("confirm");
        if (null == confirm || "".equalsIgnoreCase(confirm.trim()) || !password.equals(confirm)) {
            request.setAttribute("errMsg", "确认密码错误");
            return registerAdminPage();
        }

        user.stPasswordAndEncrype(password);


        Map result = userService.initSuperAdmin(user);
        if (Boolean.TRUE.equals(result.get("result"))) {
            //操作成功跳转到登录界面
            return "redirect:/login.action";
        } else {
            //操作失败重新回到当前页面
            return registerAdminPage();
        }
    }

}
