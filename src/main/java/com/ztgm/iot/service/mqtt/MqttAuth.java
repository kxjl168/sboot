package com.ztgm.iot.service.mqtt;

import com.ztgm.iot.dao.UserMapper;
import com.ztgm.iot.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "/mqtt")
public class MqttAuth {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserMapper userDao;

    @RequestMapping(value = "auth")
    @ResponseBody
    public void auth(HttpServletRequest request, HttpServletResponse response){
        //todo 验证 id 地址白名单
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("---- >>> "+username);
logger.info(".... emqtt validate toke: "+username);
//        List<User> ulist=userDao.selectUserByNamePwd(username, password);
        List<User> ulist=userDao.selectUserByToken(username);
        if(ulist==null||ulist.size()==0){
            response.setStatus(415);
        }else{
            response.setStatus(200);
        }


        System.out.println(username);
    }

    @RequestMapping(value = "acl")
    @ResponseBody
    public void alc(HttpServletRequest request, HttpServletResponse response){
        //todo 验证 id 地址白名单
        String username = request.getParameter("username");
        String access = request.getParameter("access");
        String clientid = request.getParameter("clientid");
        String topic = request.getParameter("topic");


        System.out.println(username);
    }
}