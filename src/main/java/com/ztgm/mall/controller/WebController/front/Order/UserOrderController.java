package com.ztgm.mall.controller.WebController.front.Order;

import com.ztgm.mall.pojo.Brand;
import com.ztgm.mall.pojo.Commodity;
import com.ztgm.mall.pojo.CommodityInstance;
import com.ztgm.mall.pojo.CommondityInstanceOptions;
import com.ztgm.mall.pojo.MUser;
import com.ztgm.mall.pojo.Order;
import com.ztgm.mall.pojo.OrderDetail;
import com.ztgm.mall.pojo.PageCondition;
import com.ztgm.mall.service.EstimateService;
import com.ztgm.mall.service.OrderService;
import com.ztgm.mall.util.DateUtil;
import com.ztgm.mall.util.UserIDUtil;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 用户订单中心
 * 
 * @author zj
 * @date 2018年6月13日
 *
 */
@Controller
@PropertySource("classpath:project.properties")
@RequestMapping("/userCenter/order")
public class UserOrderController {

	@Autowired
	private OrderService orderService;
	
	@Value("${HTTP_PATH}")
	private String FILE_SVR_HTTP_OUTER_PATH;
	

	@RequestMapping("/index")
	public String index(Map<String, Object> map) {
		
		map.put("httppath", FILE_SVR_HTTP_OUTER_PATH);
		return "/frontend/user/orderCenter";
	}

	/**
	 * 根据数组生成xls对象
	 * 
	 * @param projectreportlist
	 * @param request
	 * @return
	 * @throws Exception
	 * @date 2016-10-13
	 * @author zj
	 */
	public static HSSFWorkbook createPauseDataDetailExcel(List<String[]> projectreportlist) throws Exception {
		// 创建Excel对象
		HSSFWorkbook wb = new HSSFWorkbook();
		try {
			// 创建Excel Sheet页
			HSSFSheet deptRoleSheet = wb.createSheet("Data");
			// 在Sheet页中设置表头
			// 在Sheet页中设置表内容

			int startrow = 1;
			// Sheet页中的行对象
			HSSFRow row = null;

			int width = 30 * 256;

			HSSFCellStyle style = wb.createCellStyle();
			deptRoleSheet.setColumnWidth(0, width); // 第一个参数代表列id(从0开始),第2个参数代表宽度值

			// row = deptRoleSheet.createRow(0);

			// int j = 0;

			// //表头
			// for (int j = 0; j < projectreportlist.get(0).length; j++) {
			// deptRoleSheet.setColumnWidth(j, width);
			// row.createCell(j).setCellValue(new
			// HSSFRichTextString(projectreportlist.get(0)[j]));
			// }

			if (projectreportlist.size() >= 1)
				// 填数据
				for (int i = 0; i < projectreportlist.size(); i++) {
					String[] rowdata = projectreportlist.get(i);

					// 新建一行
					row = deptRoleSheet.createRow(i);

					// 表头
					for (int j = 0; j < projectreportlist.get(i).length; j++) {
						deptRoleSheet.setColumnWidth(j, width);
						row.createCell(j).setCellValue(new HSSFRichTextString(projectreportlist.get(i)[j]));
					}

				}
		} catch (Exception e) {
			throw e;
		}
		return wb;
	}

	private String getStateStr(Integer state) {
		String rst="待付款";
		//'订单状态(1:待支付  ，2,已取消，3：已付款, 4:待发货 ，5：待收货，6：完成   7:删除)',
		switch (state) {
		case 1:
			rst="待支付";
			break;
		case 2:
			rst="已取消";
			break;
		case 3:
			rst="已付款";
			break;
		case 4:
			rst="待发货";
			break;
		case 5:
			rst="待收货";
			break;
		case 6:
			rst="完成";
			break;
			
		default:
			break;
		}
		
		return rst;
	}

	/**
	 * 导出xls
	 * 
	 * @param order
	 * @param pageCondition
	 * @return
	 * @author zj
	 * @date 2018年6月14日
	 */
	@RequestMapping("/exportOrders")
	public void exportOrders(HttpServletResponse response, Order order, PageCondition pageCondition) {

		Subject currentUser = SecurityUtils.getSubject();
		currentUser.getPrincipal();

		List<Order> orders = orderService.showOrderList(order, pageCondition);

		List<String[]> xlsInputs = new ArrayList<String[]>();

		String[] row_head = new String[] { "订单编号", "状态", "订单备注", "付款时间", "支付方式", "总计", "订单创建时间" };
		
		String[] detail_head = new String[] { " ","商品名称", "数量", "型号", "单价", "商品总价" };

		xlsInputs.add(row_head);

		for (int i = 0; i < orders.size(); i++) {
			String[] row = new String[] { orders.get(i).getId(),getStateStr( orders.get(i).getState()),
					orders.get(i).getRemarks(),
					orders.get(i).getPayTime(), orders.get(i).getPayType().toString(),
					String.valueOf(orders.get(i).getPayMoney()), orders.get(i).getCreateTime() };

			xlsInputs.add(row);
			
			xlsInputs.add(detail_head);
			
			for (int j = 0; j < orders.get(i).getDetails().size(); j++) {
				
				OrderDetail detail= orders.get(i).getDetails().get(j);
				
				
				String xinghao="";
				for (int k = 0; k < detail.getCommodityInstance().getCommondityInstanceOptions().size(); k++) {
					 CommondityInstanceOptions options= detail.getCommodityInstance().getCommondityInstanceOptions().get(k);
					xinghao+=  options.getCommodity_option().getOption().getName()+":"+options.getCommodity_option().getValue()+" / ";
				}
				
				String[] rowdetail = new String[] {" ", detail.getCommodityInstance(). getCommodity().getName(),
						detail.getNumber().toString(),
						xinghao,
						detail.getCommodityInstance().getPrice().toString(),
						String.valueOf( detail.getCommodityInstance().getPrice()*detail.getNumber())};

				xlsInputs.add(rowdetail);
			
			}
			xlsInputs.add(new String[] {"           "});
			
			
			
		}

		try {
			HSSFWorkbook wb = createPauseDataDetailExcel(xlsInputs);
			if (wb != null) {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition",
						"attachment;filename=" + "order_" + DateUtil.getNowStr("yyyyMMddHHmmss") + ".xls");// mod
				 response.flushBuffer();  																						// by

				ServletOutputStream out = response.getOutputStream();
				wb.write(out);
				out.close();
		
			}
			
		//	return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	

	@RequestMapping("/update")
	@ResponseBody
	public String update(Order item, HttpServletRequest request) {
		JSONObject jsonObject = null;

		//String menuids = request.getParameter("menuids");
		jsonObject = orderService.updateByPrimaryKeySelective(item);

		return jsonObject.toString();

	}
	

	@RequestMapping("/showOrders")
	@ResponseBody
	public String showOrders(Order order, PageCondition pageCondition,HttpServletResponse response) {

		Subject currentUser = SecurityUtils.getSubject();
		currentUser.getPrincipal();

		String userId = UserIDUtil.getUserID(SecurityUtils.getSubject(), response);
		
		order.setUserId(userId);

		String resultString = orderService.showOrders(order, pageCondition);
		return resultString;
	}
}
