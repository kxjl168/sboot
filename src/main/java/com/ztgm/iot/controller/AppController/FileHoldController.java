package com.ztgm.iot.controller.AppController;

//import org.apache.commons.io.FileUtils;
import com.ztgm.iot.pojo.AppVersionInfo;
import com.ztgm.iot.pojo.User;

import com.ztgm.iot.service.impl.AppVersionInfoServiceImpl;
import com.ztgm.iot.service.impl.UserServiceImpl;
import com.ztgm.iot.util.AppResult;
import com.ztgm.iot.util.AppResultUtil;
import com.ztgm.iot.util.TokenUtil;
import com.ztgm.iot.util.UserIDUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/*
import com.ztgm.iot.util.TokenUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 *  app upload and get file
 */
@Controller
//@RequestMapping("/interface")
@PropertySource("classpath:project.properties")
public class FileHoldController {

    @Value("${appDownloadURL}")
    private String appDownloadURL;


    //apk 上传下载位置
    private  static final String Apk_Store_Dir="downloadapp";

    //图片根地址
    private static final String Image_Base_Dir="iot_img";
    //app 用户图片 iot_img/userpic
    private static final String User_Header_Dir="userpic";
    //app 区域背景图 iot_img/scenebg
    private static final String  Scene_Backgroud_Dir="scenebg";

    @Autowired
    private AppVersionInfoServiceImpl appVersionService;
    @Autowired
    private UserServiceImpl userService;



    @RequestMapping("/manager/etrSysconf")
    public String etrSysconf(ModelMap map, HttpServletResponse response) {

        AppVersionInfo v=appVersionService.selectLatestVersion(AppVersionInfo.AppType_Android);
        if(v==null) v=new AppVersionInfo();
        map.put("latestVersion",v);

        return "/backend/sysconfig/uploadapk";
    }

    //上传apk文件
    @RequestMapping(value = "/manager/uploadapk", method = RequestMethod.POST)
    public String  uploadApkFile(ModelMap map, HttpServletRequest req, MultipartHttpServletRequest multiReq, HttpServletResponse res) {
        try {
            String basePath = System.getProperty("user.dir")+ File.separator+Apk_Store_Dir;

            File file = new File(basePath);
            if (!file.exists()) {
                file.mkdir();
            }

            FileInputStream fs=(FileInputStream) multiReq.getFile("filename").getInputStream();
            String fname=multiReq.getFile("filename").getOriginalFilename();
            //apk 命名不符合格式  ，命名方式：xxx_vxxx.apk，_v后为年月日8位
            if(fname.lastIndexOf("_v")==-1||(fname.length()!=fname.lastIndexOf("_v")+14)){
                map.addAttribute("SuccessTag","NameError");

                AppVersionInfo v=appVersionService.selectLatestVersion(AppVersionInfo.AppType_Android);
                map.put("latestVersion",v);
                return "/backend/sysconfig/uploadapk";
            }
            FileOutputStream fos=new FileOutputStream(new File(basePath+File.separator+fname));

            byte[] buffer=new byte[1024];
            int len=0;
            while((len=fs.read(buffer))!=-1){
                fos.write(buffer, 0, len);
            }
            fos.close();
            fs.close();

            String forceUpdate=req.getParameter("forceUpdate");
            String message=req.getParameter("message");
            saveAppVersion(fname,forceUpdate, message);   // 插入一笔App 版本信息
            AppVersionInfo v=appVersionService.selectLatestVersion(AppVersionInfo.AppType_Android);
            map.put("latestVersion",v);

        } catch (Exception e) {
            e.printStackTrace();
            map.addAttribute("SuccessTag","No");
            return "/backend/sysconfig/uploadapk";
        }
        map.addAttribute("SuccessTag","Yes");

        return "/backend/sysconfig/uploadapk";

    }


    //插入一笔App 版本信息
    public void saveAppVersion(String fname,String forceUpdate,String message){

        AppVersionInfo v=new AppVersionInfo();
        String version=fname.substring(fname.lastIndexOf("_v")+2,fname.lastIndexOf(".apk"));
        v.setAppType(AppVersionInfo.AppType_Android);

        if(forceUpdate!=null&&forceUpdate.equals("true")){
            v.setForceUpdate(true);
        }else{
            v.setForceUpdate(false);
        }
        v.setMessage(message);
        v.setVersionNo(version);
        v.setCreateDate(new Date());
        v.setFileName(fname);
        appVersionService.saveAppVersionInfo(v);
    }



    @RequestMapping(value = "/interface/downloadapp2")
    public void downloadapp2(HttpServletRequest req, HttpServletResponse res) {

        String appDownloadDir = System.getProperty("user.dir")+ File.separator+Apk_Store_Dir;
        String fileName=getLatestFileName();

        res.setHeader("content-type", "application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        //        res.setContentType("application/octet-stream");
        //apk 的类型
        res.setContentType("application/vnd.android.package-archive");
        byte[] buff = new byte[1024];
//        BufferedInputStream bis = null;
//        OutputStream os = null;

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(appDownloadDir+fileName)));
             OutputStream os = res.getOutputStream()){

            byte[] buf = new byte[8192];

            int i;
            while ((i = bis.read(buf)) != -1) {
                os.write(buf, 0, i);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    //下载App apk
    @RequestMapping(value = "/interface/downloadapp")
    public void downloadapp(HttpServletRequest req, HttpServletResponse res) throws Exception{

        String appDownloadDir = System.getProperty("user.dir")+ File.separator+Apk_Store_Dir;

        String fileName=getLatestFileName();

        if(!"".equals(fileName)){

            File file=new File(appDownloadDir+File.separator+fileName);//构造要下载的文件
            if(file.exists()){
                InputStream ins=new FileInputStream(file);//构造一个读取文件的IO流对象
                BufferedInputStream bins=new BufferedInputStream(ins);//放到缓冲流里面
                OutputStream outs=res.getOutputStream();//获取文件输出IO流
                BufferedOutputStream bouts=new BufferedOutputStream(outs);
                //response.setContentType("application/x-download");//设置response内容的类型 普通下载类型
                res.setContentType("application/vnd.android.package-archive");//设置response内容的类型 下载安卓应用apk
                res.setContentLength((int) file.length());//设置文件大小
                res.setHeader("Content-disposition","attachment;filename="+ fileName);//设置头部信息
                int bytesRead = 0;
                byte[] buffer = new byte[8192];

                while ((bytesRead = bins.read(buffer, 0, 8192)) != -1) {
                    bouts.write(buffer, 0, bytesRead);
                }
                bouts.flush();//这里一定要调用flush()方法
                ins.close();
                bins.close();
                outs.close();
                bouts.close();
            }else{
                System.out.println("下载的文件不存在");
            }
        }else{
            System.out.println("下载文件时参数错误");
        }
    }

    //安装包apk，命名方式：xxx_vxxx.apk，_v后为年月日8位
    private  String getLatestFileName(){

        AppVersionInfo v=appVersionService.selectLatestVersion(AppVersionInfo.AppType_Android);
        if (v==null) return  "";
        return v.getFileName();
    }


    @RequestMapping(value = "/interface/latestVersionInfo")
    public @ResponseBody
    AppResult latestVersionInfo(){

        AppVersionInfo v=appVersionService.selectLatestVersion(AppVersionInfo.AppType_Android);
        if (v==null) AppResultUtil.success();

        String fileName=v.getFileName();
        //安装包 命名方式：xxx_vxxx.apk
        String versionNo=fileName.substring(fileName.lastIndexOf("_v")+2,fileName.lastIndexOf(".apk"));
        Map map=new HashMap();
        map.put("latestVersionNo",versionNo);
        map.put("downloadUrl",appDownloadURL);
        map.put("message",v.getMessage());
        map.put("isForceUpdate",v.getForceUpdate());

        //latestVersionNo:xxx,downloadUrl:xxx,message:xxx,isForceUpdate:xxx
        return AppResultUtil.success(map);
    }

    @RequestMapping(value = "/interface/downloadappUrl")
    public @ResponseBody String downloadAppUrl() {
        return appDownloadURL;

    }


    //上传用户头像图片
    @RequestMapping(value = "/interface/user/updHeaderPic", method = RequestMethod.POST)
    public @ResponseBody AppResult  updateHeaderPic(ModelMap map, HttpServletRequest req, MultipartHttpServletRequest multiReq, HttpServletResponse res) {
        try {
            //iot_img
            String imgRootDir=System.getProperty("user.dir")+ File.separator+Image_Base_Dir;
            File imgRootFile = new File(imgRootDir);
            if (!imgRootFile.exists()) {
                imgRootFile.mkdir();
            }
            //iot_img/userpic
            String basePath = imgRootFile+File.separator+User_Header_Dir;
            File file = new File(basePath);
            if (!file.exists()) {
                file.mkdir();
            }

            // 删除已有图片
            User u= TokenUtil.getCurrentUser();
            File existsFile=new File(basePath+File.separator+getFileNameInDir(basePath,u.getId()));
            if(existsFile.exists()) existsFile.delete();

            FileInputStream fs=(FileInputStream) multiReq.getFile("picFile").getInputStream();
            String fname=multiReq.getFile("picFile").getOriginalFilename();
            String extendName=fname.substring(fname.lastIndexOf("."));

            FileOutputStream fos=new FileOutputStream(new File(basePath+File.separator+u.getId()+extendName));

            byte[] buffer=new byte[1024];
            int len=0;
            while((len=fs.read(buffer))!=-1){
                fos.write(buffer, 0, len);
            }
            fos.close();
            fs.close();

            //返回获取路径
            return AppResultUtil.success("/interface/user/headerpic?Token="+u.getToken());
        } catch (Exception e) {
            e.printStackTrace();
            return AppResultUtil.fail();
        }

    }

    //获取用户头像
    @RequestMapping(value = "/interface/user/headerpic")
    public void holdUserHeaderPic(HttpServletRequest req, HttpServletResponse res) {

        String token=req.getParameter("Token");
        User user=userService.getUserByToken(token);

        String dir=System.getProperty("user.dir")+ File.separator+Image_Base_Dir+File.separator+User_Header_Dir;
        String fileName=getFileNameInDir(dir,user.getId());
        String basePath = dir+File.separator+fileName;
        File file = new File(basePath);
        if (!file.exists()) {
            return;
        }

        res.setHeader("content-type", "application/octet-stream");
//        res.setHeader("content-type", "image/x-png");
//        res.setHeader("Content-Disposition", "inline;filename=" + token+".png");                          //直接打开图片
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);           //下载图片

        byte[] buff = new byte[1024];

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
             OutputStream os = res.getOutputStream()){

            byte[] buf = new byte[8192];

            int i;
            while ((i = bis.read(buf)) != -1) {
                os.write(buf, 0, i);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    //上传场景背景图片
    @RequestMapping(value = "/manager/scene/uploadSceneBg", method = RequestMethod.POST)
    public  void   uploadSceneBackgroudPic(ModelMap map, HttpServletRequest req, MultipartHttpServletRequest multiReq, HttpServletResponse res) {
        try {
            //iot_img
            String imgRootDir=System.getProperty("user.dir")+ File.separator+Image_Base_Dir;
            File imgRootFile = new File(imgRootDir);
            if (!imgRootFile.exists()) {
                imgRootFile.mkdir();
            }
            //iot_img/scenebg
            String basePath = imgRootFile+File.separator+Scene_Backgroud_Dir;
            File file = new File(basePath);
            if (!file.exists()) {
                file.mkdir();
            }

            // 删除已有图片
            Subject subject = SecurityUtils.getSubject();
            Map principal = (Map) subject.getPrincipal();
            String userId = (String) principal.get("userId");
            File existsFile=new File(basePath+File.separator+getFileNameInDir(basePath,userId));
            if(existsFile.exists()) existsFile.delete();

            FileInputStream fs=(FileInputStream) multiReq.getFile("picFile").getInputStream();
            String fname=multiReq.getFile("picFile").getOriginalFilename();
            String extendName=fname.substring(fname.lastIndexOf("."));

            FileOutputStream fos=new FileOutputStream(new File(basePath+File.separator+userId+extendName));

            byte[] buffer=new byte[1024];
            int len=0;
            while((len=fs.read(buffer))!=-1){
                fos.write(buffer, 0, len);
            }
            fos.close();
            fs.close();

            res.sendRedirect("/manager/scene/findSceneDevice?uploadappbg=true");

        } catch (Exception e) {
            e.printStackTrace();
            try {
                res.sendRedirect("/manager/scene/findSceneDevice?uploadappbg=false");
            }catch (Exception ee){

            }
        }

    }

    //获取场景背景图片
    @RequestMapping(value = "/interface/scene/holdbackground")
    public void holdSceneBackground(HttpServletRequest req, HttpServletResponse res) {

        User user=TokenUtil.getCurrentUser();

        String dir=System.getProperty("user.dir")+ File.separator+Image_Base_Dir+File.separator+Scene_Backgroud_Dir;
        String fileName=getFileNameInDir(dir,user.getId());
        String basePath = dir+File.separator+fileName;
        File file = new File(basePath);
        if (!file.exists()) {
            //使用默认
            fileName="A_Default.jpg";
            file = new File(dir+File.separator+"A_Default.jpg");
            if (!file.exists()) return;
        }

        res.setHeader("content-type", "application/octet-stream");
//        res.setHeader("content-type", "image/x-png");
//        res.setHeader("Content-Disposition", "inline;filename=" + token+".png");                          //直接打开图片
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);           //下载图片

        byte[] buff = new byte[1024];

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
             OutputStream os = res.getOutputStream()){

            byte[] buf = new byte[8192];

            int i;
            while ((i = bis.read(buf)) != -1) {
                os.write(buf, 0, i);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    //在某目录下查找指定 前缀文件
    private  String getFileNameInDir(String dir,String prefix){
        File file=new File(dir);
        String[] fileNames=file.list();
        if(fileNames==null) return null;
        for(String name:fileNames){
            if (name.startsWith(prefix)) return name;
        }
        return null;
    }




/*
    @RequestMapping(value="/filehold2")
    public ResponseEntity<byte[]> download(HttpServletRequest request,
                                           @RequestParam("fileName") String filename)throws Exception {
        //下载文件路径
        String path = request.getServletContext().getRealPath("/images/");
        File file = new File(path + File.separator + filename);
        HttpHeaders headers = new HttpHeaders();
        //下载显示的文件名，解决中文名称乱码问题
        String downloadFielName = new String(filename.getBytes("UTF-8"),"iso-8859-1");
        //通知浏览器以attachment（下载方式）打开图片
        headers.setContentDispositionFormData("attachment", downloadFielName);
        //application/octet-stream ： 二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }
*/
}

