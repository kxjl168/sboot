package com.ztgm.mall.controller.WebController.backend;

import com.github.pagehelper.Page;
import com.ztgm.mall.pojo.CommodityFirstClassify;
import com.ztgm.mall.service.BrandService;
import com.ztgm.mall.service.CommodityClassifyService;
import com.ztgm.mall.service.CommodityService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private CommodityClassifyService commodityClassifyService;

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private BrandService brandService;

    /*@Value("${HTTP_PATH}")
    private String filePathPrefix;*/

    @RequestMapping("/index")
    public String index(Map<String, Object> map) {
        map.put("name", "admin");
        map.put("admin", "admin2");

        Subject currentUser = SecurityUtils.getSubject();
        currentUser.getPrincipal();


        //首页数据
        List<CommodityFirstClassify> list = commodityClassifyService.getTopClassify();
        map.put("list", list);


        return "/frontend/public/index";
    }


    private String parentClms[] = {"first_classify_id", "second_classify_id"};

    /**
     * sort :
     * 1：销降    价升     上升
     * 2：销降    价降     上降
     * 3：销降    价降     上升
     * 4：销降    价升     上降
     * 5：销升    价降     上降
     * 6：销升    价降     上升
     * 7：销升    价升     上降
     * 8：销升    价升     上升
     *
     * @param level
     * @param page
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "/classify/{level}/{sort}/{page}/{brand}/{option}/{id}.html")
    public String classifyPage(@PathVariable("level") Integer level,
                               @PathVariable("sort") Integer sort,
                               @PathVariable("page") Integer page,
                               @PathVariable("brand") String brand,
                               @PathVariable("option") String option,
                               @PathVariable("id") String id,
                               Map<String, Object> map) {
        Integer limit = 10;
        String tmpClassifyId = id;

        //导航区域
        List navs = new ArrayList(3);
        Map classify = null;
        for (int i = level; 0 < i; i--) {
            classify = commodityClassifyService.getClassifyById(i, tmpClassifyId);
            navs.add(classify);

            if (!Integer.valueOf(1).equals(i)) {
                tmpClassifyId = (String) classify.get(parentClms[i - 2]);
            }
        }
        //分类 导航 改为 从 1 - 3 级
        Collections.reverse(navs);

        //品牌
        List brands = brandService.getClassifyBrands(level, id);


        Map scdClassifie;
        Map thdClassifie;
        //二级分类list
        List scdClassifies = null;
        //三级分类list
        List thdClassifies = null;
        if (Integer.valueOf(1).equals(level)) {
            scdClassifies = commodityClassifyService.getSubClassifies(level, id);
            for (int i = 0; i < scdClassifies.size(); i++) {
                scdClassifie = (Map) scdClassifies.get(i);
                thdClassifies = commodityClassifyService.getSubClassifies(level + 1, (String) scdClassifie.get("id"));
                scdClassifie.put("thdClassifies", thdClassifies);
            }
        }

        if (Integer.valueOf(2).equals(level)) {
            scdClassifies = new ArrayList();
            scdClassifie = commodityClassifyService.getClassifyById(level, id);
            scdClassifies.add(scdClassifie);

            //获取三级分类
            thdClassifies = commodityClassifyService.getSubClassifies(level, id);
            scdClassifie.put("thdClassifies", thdClassifies);
        }

        if (Integer.valueOf(3).equals(level)) {

            //三级分类
            thdClassifies = new ArrayList();
            thdClassifie = commodityClassifyService.getClassifyById(level, id);
            thdClassifies.add(thdClassifie);

            //二级分类
            scdClassifies = new ArrayList();
            scdClassifie = commodityClassifyService.getClassifyById(2, (String) thdClassifie.get("second_classify_id"));
            scdClassifie.put("thdClassifies", thdClassifies);
            scdClassifies.add(scdClassifie);
        }


        //商品选项
        List options = commodityService.getOptionsByClassify(level,id);


        //商品
        Page commodityInstanceList = commodityService.selectCommodityListByClassify(level, sort, page, brand, option, id, limit);

        //页面属性
        map.put("level", level);
        map.put("sort", sort);
        map.put("page", page);
        map.put("id", id);


        map.put("classify", classify);
        map.put("navs", navs);
        map.put("brands", brands);
        map.put("scdClassifies", scdClassifies);
        map.put("ciList", commodityInstanceList.getResult());
        map.put("pages", commodityInstanceList.getPages());
        map.put("total", commodityInstanceList.getTotal());

        map.put("options",options);

        return "/frontend/public/search";
    }

    @RequestMapping(value = "/commodity/{id}.html")
    public String commodityPage(@PathVariable("id") String id, Map<String, Object> map) {

        //CommodityInstance commodityInstance = commodityService.selectByPrimaryKey(id);

        return "/frontend/public/index";
    }

    @RequestMapping("/search")
    public String search(Map<String, Object> map) {
        map.put("name", "admin");
        map.put("admin", "admin2");

        Subject currentUser = SecurityUtils.getSubject();
        currentUser.getPrincipal();

        List<CommodityFirstClassify> list = commodityClassifyService.getTopClassify();
        map.put("list", list);

        return "/frontend/public/search";
    }


}
