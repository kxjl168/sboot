package com.ztgm.iot.controller.AppController;

import com.ztgm.iot.pojo.GroupUser;
import com.ztgm.iot.pojo.User;
import com.ztgm.iot.util.AppResult;
import com.ztgm.iot.util.AppResultUtil;
import com.ztgm.iot.util.PasswordUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.*;

@Controller
@RequestMapping("/interface/user")
public class ImageCodeController {

    private static Map<String,Date> codeMap=new HashMap<>();
    /**
     * App 注册 图片验证码
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value="/imgValidateCode")
    public void validateCode(HttpServletRequest request, HttpServletResponse response)  throws Exception{
        int width = 80;
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
        String capstr =null;
        Random rdm = new Random();
        do{
            String hash1 = Integer.toHexString(rdm.nextInt());
            System.out.print(hash1);
            capstr = hash1.substring(0, 4);
        } while (codeMap.get(capstr)!=null);
        //将生成的验证码存入session
//        request.getSession().setAttribute("validateCode", capstr);
        codeMap.put(capstr,new Date());


        // make some confusion
        for (int i = 0; i < 50; i++) {
            int x = rdm.nextInt(width);
            int y = rdm.nextInt(height);
            g.drawOval(x, y, 0, 0);
        }
        // generate a random code


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


    @RequestMapping(value = "/chkImgValCode")
    public @ResponseBody  AppResult chkImgValCode(String valCode){

        if(codeMap.get(valCode)!=null){
            codeMap.remove(valCode);
            return AppResultUtil.success();
        }else{
            return AppResultUtil.fail();
        }

    }

    //以一个固定30分钟调用一次，删除过期的图片码
    @Scheduled(fixedRate  = 30*60*1000)
    public void removeExpiredCode(){
        long curTime=System.currentTimeMillis();
//        System.out.println("now --->>> "+curTime);
        for(Iterator<Map.Entry<String,Date>> it=codeMap.entrySet().iterator();it.hasNext();){
            Map.Entry<String,Date> me=it.next();
            long mt=me.getValue().getTime();
            if(curTime-mt>1000*60*30){
                it.remove();
            }
        }
    }

}
