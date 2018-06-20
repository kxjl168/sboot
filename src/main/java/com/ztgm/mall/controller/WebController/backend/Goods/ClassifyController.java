package com.ztgm.mall.controller.WebController.backend.Goods;

import com.google.gson.Gson;
import com.ztgm.mall.pojo.CommodityFirstClassify;
import com.ztgm.mall.service.CommodityClassifyService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @author handsomeXiao
 * @date 2018/5/30 11:51
 */
@Controller
@RequestMapping(value = "/manager/classify")
public class ClassifyController {

    @Autowired
    private CommodityClassifyService commodityClassifyService;

    private Gson gson = new Gson();

    @RequestMapping(value = "/index")
    public String indexPage (Map<String, Object> model) {
        List<CommodityFirstClassify> commodityFirstClassifyList = commodityClassifyService.showAllClassify ();
        model.put("commodityClassifyList", commodityFirstClassifyList);
        return "/backend/goods/classify/index";
    }

    @RequestMapping(value = "/getAllTopClassify")
    @ResponseBody
    public List getAllTopClassify (String level) {
        return commodityClassifyService.getAllTopClassify (level);
    }
    
    
    /**
     * 获取子分类
     * @param request
     * @return
     * @author zj
     * @date 2018年6月11日
     */
    @RequestMapping(value = "/listClass")
    @ResponseBody
    public String listClass(HttpServletRequest request){
        String pid = request.getParameter("pid");
        String level = request.getParameter("level");
        
        String resultString = gson.toJson(commodityClassifyService.getClassifyByPidAndLevel (level,pid));
       // System.out.println(resultString);
        return resultString;
    }
    

    @RequestMapping(value = "/classifyOperate")
    @ResponseBody
    public Integer classifyOperate (@RequestParam Map<String, String> parameter) {
        return commodityClassifyService.classifyOperate (parameter);
    }

    @RequestMapping(value = "/deleteClassify")
    @ResponseBody
    public Integer deleteClassify (@RequestParam Map<String, String> parameter) {
        return commodityClassifyService.deleteClassify (parameter);
    }

    @RequestMapping(value = "/uploadClassifyFile")
    @ResponseBody
    public String saveFile(StandardMultipartHttpServletRequest request){
        String id = request.getParameter("id");
        String type = request.getParameter("type");
        MultipartFile file = request.getFile("file_data");
        String resultString = gson.toJson(commodityClassifyService.classifyPictureUpload (id, type, file));
        //System.out.println(resultString);
        return resultString;
    }
}
