package com.ztgm.iot.controller.AppController;

import com.ztgm.iot.controller.AppController.parm.CodeParam;
import com.ztgm.iot.controller.AppController.parm.MessParam;
import com.ztgm.iot.pojo.GroupUser;
import com.ztgm.iot.pojo.Sms;
import com.ztgm.iot.pojo.User;
import com.ztgm.iot.service.impl.GroupUserServiceImpl;
import com.ztgm.iot.service.impl.SmsServiceImpl;
import com.ztgm.iot.service.impl.UserServiceImpl;
import com.ztgm.iot.service.mqtt.EmqPublisher;
import com.ztgm.iot.util.*;
import com.ztgm.iot.util.sendpost.HttpSendPost;
import com.ztgm.iot.util.sendpost.SendPostResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * app 用户
 */
@RestController
@RequestMapping("/interface/user")
@PropertySource("classpath:wxinconfig.properties")
public class AUserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private GroupUserServiceImpl groupUserService;
    @Autowired
    private EmqPublisher emq;
    @Autowired
    private SmsServiceImpl smsService;
    @Autowired
    private CodeParam appCode;
    @Autowired
    private MessParam appMess;

    @Value("${emqtt.broker}")
    private String emqttServerUrl;

    @Value(value = "${wxin_appid}")
    private String wxinAppId;

    @Value(value = "${wxin_app_secret}")
    private String wxinAppSecret;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public AppResult userLogin(String username, String password) {
        if (username == null || "".equals(username)) {
            return AppResultUtil.fail();
        } else if (password == null || "".equals(password)) {
            return AppResultUtil.fail();
        }
        password = PasswordUtil.encrypt(password, username);
        User u = userService.login(username, password);
        if (u == null) {
            return AppResultUtil.fire("01", "手机号或密码错误", null);
        }
        String token = UUID.randomUUID().toString();
        userService.updateUserToke(u.getId(), token);
        Map<String, Object> map = new HashMap<>();
        //获取图像
        u.setHeadImgUrl("/interface/user/headerpic?Token=" + token);
        map.put("user", u);
        map.put("Token", token);

        GroupUser gu = groupUserService.findGroupByUser(u.getId());
        map.put("group", gu);
        map.put("emqUrl", emqttServerUrl);
        return AppResultUtil.success(map);
    }

    @RequestMapping(value = "/wxinlogin", method = RequestMethod.POST)
    public AppResult wxinUserLogin(String code, String iv, String encryptedData, String userCity, String userNikeName, String userSex, String userProvince, String userImage) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("appid", wxinAppId);
            map.put("secret", wxinAppSecret);
            map.put("js_code", code);
            map.put("grant_type", "authorization_code");

            SendPostResponse wzResponse = new HttpSendPost().doPost("https://api.weixin.qq.com/sns/jscode2session?appid=" + wxinAppId + "&secret=" + wxinAppSecret + "&js_code=" + code + "&grant_type=authorization_code", "");
            JSONObject jsonObject = new JSONObject(wzResponse.getData());
            String session_key = jsonObject.getString("session_key");
            String openid = jsonObject.getString("openid");
            String userPhoneInfo = AesCBC.getInstance().decrypt(encryptedData, session_key, iv);

            if (userPhoneInfo == null || userPhoneInfo.equals("")) {
                return AppResultUtil.fire("01", "手机号获取失败", null);
            }
            String userPhone = new JSONObject(userPhoneInfo).getString("phoneNumber");
            User user = userService.getUserByPhone(userPhone);
            if (user == null) {
                user = new User();
                user.setUsername(userNikeName);
                user.setPassword("");
                user.setTelephone(userPhone);
                user.setHeadImgUrl(userImage);
                user.setRole("4");     // 普通用户ID
                userService.saveUser(user);
            }
            user = userService.getUserByPhone(userPhone);

            String token = UUID.randomUUID().toString();
            userService.updateUserToke(user.getId(), token);
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("user", user);
            userMap.put("Token", token);
            GroupUser gu = groupUserService.findGroupByUser(user.getId());
            userMap.put("group", gu);
            userMap.put("emqUrl", emqttServerUrl);
            return AppResultUtil.success(userMap);
        } catch (Exception e) {
            e.printStackTrace();
            return AppResultUtil.fire("01", "手机号获取失败", null);
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public AppResult register(String username, String password, String validCode) {
        if (username == null || "".equals(username)) {
            return AppResultUtil.fail();
        } else if (password == null || "".equals(password)) {
            return AppResultUtil.fail();
        }
        try {
            User eu = userService.getUserByPhone(username);
            if (eu != null) {
//                return AppResultUtil.fire(User_Code_U002,"this phone has register. ",null);
                return AppResultUtil.fire(appCode.U002, appMess.U002, null);
            }
            Sms sms = smsService.findValidateCodeSmsByPhone(username);
            if (sms == null) {
                return AppResultUtil.fire(appCode.U003, appMess.U003, null);
            }
            if (sms != null && !sms.getMsg().equals(validCode)) {
//                return AppResultUtil.fire(User_Code_U003,"validate code error or expired. ",null);
                return AppResultUtil.fire(appCode.U003, appMess.U003, null);
            }
            User u = new User();
            u.setUsername(username);
            u.setPassword(password);
            u.setTelephone(username);
            u.setRole("4");
            userService.saveUser(u);

            return AppResultUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AppResultUtil.fail();
        }

    }

    @RequestMapping(value = "/updatepw", method = RequestMethod.POST)
    public AppResult updatepw(String oldPassword, String newPassword) {
        if (oldPassword == null || "".equals(oldPassword)) {
            return AppResultUtil.fail();
        } else if (newPassword == null || "".equals(newPassword)) {
            return AppResultUtil.fail();
        }
        try {
            User eu = TokenUtil.getCurrentUser();

            oldPassword = PasswordUtil.encrypt(oldPassword, eu.getUsername());
            if (!oldPassword.equals(eu.getPassword())) {
                return AppResultUtil.fire(appCode.U004, appMess.U004, null);
            }
            eu.setPassword(PasswordUtil.encrypt(newPassword, eu.getUsername()));
            userService.directUpdateUser(eu);

            return AppResultUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AppResultUtil.fail();
        }

    }

    @RequestMapping(value = "/forgetpw", method = RequestMethod.POST)
    public AppResult forgetpw(String username, String password, String validCode) {
        try {
            User eu = userService.getUserByPhone(username);
            if (eu == null) {
                return AppResultUtil.fire(AppResultUtil.no_user_code, AppResultUtil.no_user_message, null);
            }
            Sms sms = smsService.findValidateCodeSmsByPhone(username);
            if (sms == null) {
                return AppResultUtil.fire(appCode.U003, appMess.U003, null);
            }
            if (!sms.getMsg().equals(validCode)) {
                return AppResultUtil.fire(appCode.U003, appMess.U003, null);
            }
            password = PasswordUtil.encrypt(password, username);
            eu.setPassword(password);
            userService.directUpdateUser(eu);

            return AppResultUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AppResultUtil.fail();
        }

    }


    @RequestMapping(value = "/emqtt")
    public AppResult emqttTest(String topic, String message) {
        if (topic == null || "".equals(topic)) {
            return AppResultUtil.fail();
        } else if (message == null || "".equals(message)) {
            return AppResultUtil.fail();
        }
        emq.send(topic, message, TokenUtil.getCurrentUser().getToken(), TokenUtil.getCurrentUser().getToken());
        return AppResultUtil.success();
    }


    /**
     * 权限转移（设备控制权）
     */
    @RequestMapping("/permissionTransfer")
    public AppResult controlPermissionTransfer(String phone) {
        if (phone == null || "".equals(phone)) {
            return AppResultUtil.fail();
        }
        try {
            User u = userService.getUserByPhone(phone);
            if (u == null) {
                return AppResultUtil.fire(AppResultUtil.no_user_code, AppResultUtil.no_user_message, null);
            }
            // to do 验证是否家庭组成员
            List<User> list = groupUserService.findGroupMemberList(TokenUtil.getCurGroupUser().getGroupId());
            boolean inGroup = false;
            for (User gu : list) {
                if (u.getId().equals(gu.getId())) {
                    inGroup = true;
                    break;
                }
            }
            if (!inGroup) {
//                return AppResultUtil.fire(User_Code_U001,"This user not in group. ",null);
                return AppResultUtil.fire(appCode.U001, appMess.U001, null);
            }
            userService.controlPermissionTransfer(TokenUtil.getCurrentUser().getId(), u.getId());
            return AppResultUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AppResultUtil.fail();
        }
    }


    @RequestMapping("/validateCode")
    public AppResult validateCode(String phone) {
        if (phone == null || "".equals(phone)) {
            return AppResultUtil.fail();
        }
        try {
            User eu = userService.getUserByPhone(phone);
            if (eu != null) {
                return AppResultUtil.fire(appCode.U002, appMess.U002, null);
            }
            smsService.SendRegisterValidateCodeSms(phone);
            return AppResultUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AppResultUtil.fail();
        }

    }

    /**
     * 用户详细信息
     */
    @RequestMapping("/userInformation")
    public String getUserInformation(String username, String password) {
        return "login success....";
    }

    @RequestMapping(value = "/getEmqttServer")
    public AppResult getEmqttServer() {
        return AppResultUtil.success(this.emqttServerUrl);
    }

}
