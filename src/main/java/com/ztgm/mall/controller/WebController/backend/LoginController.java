package com.ztgm.mall.controller.WebController.backend;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Map;
import java.util.Random;

@Controller
public class LoginController {


    @RequestMapping("/index")
    public String index(Map<String, Object> map) {
        map.put("name", "admin" );
        map.put("admin", "admin2" );

        Subject currentUser = SecurityUtils.getSubject();
        currentUser.getPrincipal();

        return "/frontend/user/welcome";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        //根据shiro返回的异常类路径判断，抛出指定异常信息
        if(exceptionClassName!=null){
            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                //最终会抛给异常处理器
                model.addAttribute("error","账号不存在");
            } else if (IncorrectCredentialsException.class.getName().equals(
                    exceptionClassName)) {
                model.addAttribute("error","用户名/密码错误");
            } else if("randomCodeError".equals(exceptionClassName)){
                model.addAttribute("error","验证码错误");
            }else {
                model.addAttribute("error","未知异常");
            }
        }
        return "/frontend/login/login";
    }

    /*@RequestMapping("/logout")
    public String logout(HttpServletRequest request,RedirectAttributes redirectAttributes) {
        Subject subject = SecurityUtils.getSubject();
        if(null!=subject && subject.isAuthenticated()){
            subject.logout();
        }

        return "redirect:/login.action";
    }*/


    /**
     * 图片验证码
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value="/validateCode")
    public void validateCode(HttpServletRequest request, HttpServletResponse response)  throws Exception{
        int width = 60;
        int height = 32;
        //create the image
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        // set the background color
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0, 0, width, height);
        // draw the border
        g.setColor(Color.black);
        g.drawRect(0, 0, width - 1, height - 1);
        // create a random instance to generate the codes
        Random rdm = new Random();
        String hash1 = Integer.toHexString(rdm.nextInt());
        System.out.print(hash1);
        // make some confusion
        for (int i = 0; i < 50; i++) {
            int x = rdm.nextInt(width);
            int y = rdm.nextInt(height);
            g.drawOval(x, y, 0, 0);
        }
        // generate a random code
        String capstr = hash1.substring(0, 4);
        //将生成的验证码存入session
        request.getSession().setAttribute("validateCode", capstr);
        g.setColor(new Color(0, 100, 0));
        g.setFont(new Font("Candara", Font.BOLD, 24));
        g.drawString(capstr, 8, 24);
        g.dispose();
        //输出图片
        response.setContentType("image/jpeg");

        OutputStream strm = response.getOutputStream();
        ImageIO.write(image, "jpeg", strm);
        strm.close();
    }






}
