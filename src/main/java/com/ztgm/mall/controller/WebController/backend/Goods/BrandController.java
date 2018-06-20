package com.ztgm.mall.controller.WebController.backend.Goods;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.ztgm.mall.pojo.Brand;
import com.ztgm.mall.pojo.Commodity;
import com.ztgm.mall.pojo.PageCondition;
import com.ztgm.mall.service.BrandService;
import com.ztgm.mall.service.OptionService;
import com.ztgm.mall.util.Message;
import com.ztgm.mall.util.PageUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.ztgm.mall.util.PageUtil.packageTableData;

import java.util.List;
import java.util.Map;

/**
 * 商品品牌
 * @author zj
 * @date 2018年5月25日
 *
 */
@Controller
@RequestMapping("/manager/brand")
@PropertySource("classpath:project.properties")
public class BrandController {

	


	//文件目录外网访问地址
		@Value("${HTTP_PATH}")
		private String FILE_SVR_HTTP_OUTER_PATH;
		
		
	
	@Autowired
	private BrandService brandService;
	

    @RequestMapping("/index")
    public String index(Map<String, Object> map) {
        map.put("name", "admin" );
        map.put("admin", "admin2" );
        
        map.put("httppath", FILE_SVR_HTTP_OUTER_PATH);
//
//        Subject currentUser = SecurityUtils.getSubject();
//        currentUser.getPrincipal();

        return "/backend/goods/brand/index";
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
	public String list(Brand q,PageCondition pageCondition, HttpServletRequest request,
			HttpServletResponse response) {


		String resultString = "";

		Page page = PageUtil.getPage(pageCondition);
		List<Brand> rst = brandService.selectBrandList(q);
		try {
			resultString = packageTableData(page, rst);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return resultString;// responseData(request, response, datastr);

		
		
		

	}

	@RequestMapping("/load")
	@ResponseBody
	public Brand load(HttpServletRequest request) {
		String id = request.getParameter("id");

		Brand p = brandService.selectByPrimaryKey(id);

		return p;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Message updateNoticeRead(HttpServletRequest request) {
		String id = request.getParameter("id");
		Message msg = new Message();
		int result = brandService.deleteByPrimaryKey(id);
		if (result == 1) {
			msg.setBol(true);
		}
		return msg;
	}

	@RequestMapping("/save")
	@ResponseBody
	public String save(Brand role, HttpServletRequest request) {
		JSONObject jsonObject = null;

		///String menuids = request.getParameter("menuids");

		jsonObject = brandService.insertSelective(role);

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
	public String saveOrUpdate(Brand item, HttpServletRequest request) {
		JSONObject jsonObject = null;
		try {

		
			if (null == item.getId() || "".equals(item.getId())) {
				jsonObject = brandService.insertSelective(item);
			} else {
				jsonObject = brandService.updateByPrimaryKeySelective(item);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonObject.toString();
	}




	@RequestMapping("/update")
	@ResponseBody
	public String update(Brand role, HttpServletRequest request) {
		JSONObject jsonObject = null;

		//String menuids = request.getParameter("menuids");
		jsonObject = brandService.updateByPrimaryKeySelective(role);

		return jsonObject.toString();

	}


}
