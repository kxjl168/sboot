package com.ztgm.mall.controller.WebController.backend.Goods;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.ztgm.mall.pojo.AttributeDef;
import com.ztgm.mall.pojo.Brand;
import com.ztgm.mall.pojo.Commodity;
import com.ztgm.mall.pojo.CommodityAttribute;
import com.ztgm.mall.pojo.CommodityInstance;
import com.ztgm.mall.pojo.CommodityOption;
import com.ztgm.mall.pojo.CommondityImage;
import com.ztgm.mall.pojo.OptionDef;
import com.ztgm.mall.pojo.PageCondition;
import com.ztgm.mall.service.AttributeService;
import com.ztgm.mall.service.BrandService;
import com.ztgm.mall.service.CommodityDetailService;
import com.ztgm.mall.service.CommodityService;
import com.ztgm.mall.service.OptionService;
import com.ztgm.mall.util.Message;
import com.ztgm.mall.util.PageUtil;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.ztgm.mall.util.PageUtil.packageTableData;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 商品
 * @author zj
 * @date 2018年5月25日
 *
 */
@Controller
@RequestMapping("/manager/goodsDetail")
public class GoodsDetailController {




	//文件目录外网访问地址
		@Value("${HTTP_PATH}")
		private String FILE_SVR_HTTP_OUTER_PATH;
		
		
	
	@Autowired
	private CommodityDetailService goodsDetailService;
	

	@Autowired
	private BrandService barndService;
	
	@Autowired
	private AttributeService  attributeService;
	
	@Autowired
	private OptionService  optionService;
	

    @RequestMapping("/index")
    public String index(Map<String, Object> map) {
        map.put("name", "admin" );
        map.put("admin", "admin2" );
        
        map.put("httppath", FILE_SVR_HTTP_OUTER_PATH);

        Brand bq=new Brand();
       List<Brand> brandlist= barndService.selectBrandList(bq);
       map.put("brandlist", brandlist);
       
       
       AttributeDef aq=new AttributeDef();
       aq.setMandatory(1);//必选
       
       List<AttributeDef> attrmustlist= attributeService.selectAttributeDefList(aq);
       map.put("attrmustlist", attrmustlist);
       
       
        
        
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.getPrincipal();

        return "/backend/goods/gd/detail";
    }

	/**
	 * bootstrap tables jquery查询数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author zj
	 * @date 2018年5月9日
	 */
	@RequestMapping("/list")
	@ResponseBody
	public String list(Commodity cq,PageCondition pageCondition, HttpServletRequest request,
			HttpServletResponse response) {

		
		String resultString = "";

		
		CommodityInstance q = new CommodityInstance();
		q.setCommodity(cq);
		
		Page page = PageUtil.getPage(pageCondition);
		List<CommodityInstance> rst = goodsDetailService.selectCommodityList(q);

		try {
			resultString = packageTableData(page, rst);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return resultString;// responseData(request, response, datastr);

		
		

	}

	@RequestMapping("/load")
	@ResponseBody
	public CommodityInstance load(HttpServletRequest request) {
		String id = request.getParameter("id");

		CommodityInstance p = goodsDetailService.selectByPrimaryKey(id);

		return p;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Message delete(HttpServletRequest request) {
		String id = request.getParameter("id");
		//删除实例，设置状态
		Message msg = new Message();
		int result = goodsDetailService.deleteByPrimaryKey(id);
		if (result == 1) {
			msg.setBol(true);
		}
		return msg;
	}

	@RequestMapping("/save")
	@ResponseBody
	public String save(CommodityInstance role, HttpServletRequest request) {
		JSONObject jsonObject = null;

		///String menuids = request.getParameter("menuids");

		jsonObject = goodsDetailService.insertSelective(role);

		return jsonObject.toString();

	}
	

	/**
	 * 
	 *
	 * @param user
	 * @return
	 */
	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	//@Transactional
	public String saveOrUpdate(CommodityInstance item, HttpServletRequest request) {
		JSONObject jsonObject = null;
		try {

		
		
			if (null == item.getId() || "".equals(item.getId())) {
				jsonObject = goodsDetailService.insertSelective(item);
			} else {
				jsonObject = goodsDetailService.updateByPrimaryKeySelective(item);
			}

		} catch (Exception e) {
			//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}

		return jsonObject.toString();
	}


	/**
	 * 下架
	 * @param role
	 * @param request
	 * @return
	 * @author zj
	 * @date 2018年6月7日
	 */
	@RequestMapping("/down")
	@ResponseBody
	public String down(CommodityInstance role, HttpServletRequest request) {
		JSONObject jsonObject = null;
	
		
		role.setStatus(2);
		jsonObject = goodsDetailService.updateByPrimaryKeySelective(role);
	
		return jsonObject.toString();

	}
	


	@RequestMapping("/update")
	@ResponseBody
	public String update(CommodityInstance role, HttpServletRequest request) {
		JSONObject jsonObject = null;
	
		
		//String menuids = request.getParameter("menuids");
		jsonObject = goodsDetailService.updateByPrimaryKeySelective(role);

		return jsonObject.toString();

	}


}
