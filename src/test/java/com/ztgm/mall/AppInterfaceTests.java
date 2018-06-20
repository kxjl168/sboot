package com.ztgm.mall;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.shiro.util.ThreadContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Rollback(true) // 自动回滚
/**
 * 接口测试
 * 
 * @author zj
 * @date 2018年6月20日
 *
 */
public class AppInterfaceTests {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	private Logger logger = LoggerFactory.getLogger(AppInterfaceTests.class);

	@Before
	public void setup() {

		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		// 模拟http请求，并模拟session等，保持登陆
		org.apache.shiro.mgt.SecurityManager securityManger = Mockito.mock(org.apache.shiro.mgt.SecurityManager.class,
				Mockito.RETURNS_DEEP_STUBS);
		ThreadContext.bind(securityManger);
	}

	public void testLogin() throws Exception {

	}

	@Test
	public void dataUpdate() {
		// Message msg = new Message();
		try {

			Date bdate = new Date();

			// 模拟调用接口,打印结果
			 mockMvc.perform(
					(post("/interface/order/showOrders")
							.param("state", "")
							.param("Token", "111")
							.param("offset", "0")
							.param("p3", "Driver")
							.contentType(MediaType.APPLICATION_FORM_URLENCODED) // 数据的格式
					))
			.andExpect(status().isOk()) // 返回的状态是200
					.andDo(print()) // 打印出请求和相应的内容
					.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
			 
			 System.out.println("========================");
			 
			 String commetdata="[{\"gid\":\"c8cb4aece819436899f4dd1eb7fa1733\",\"iid\":\"360dcd1d3e2043978933dcc0ee58df8d\",\"oid\":\"201801017\",\"odid\":\"6\",\"value\":\"%E9%9D%9E%E5%B8%B8%E5%A5%BD%E7%9A%84%E4%BA%A7%E5%93%81%EF%BC%81%EF%BC%81\",\"star\":\"3\",\"isanony\":\"1\"}]";
			// 模拟调用接口,打印结果
			 mockMvc.perform(
					(post("/interface/order/commet")
							.param("Token", "111")
							.param("p3", "Driver")
							//.contentType(MediaType.APPLICATION_FORM_URLENCODED)
							.content("data="+commetdata)
							.contentType(MediaType.APPLICATION_FORM_URLENCODED)
							.characterEncoding("utf-8")
						
							 // 数据的格式
					))
			.andExpect(status().isOk()) // 返回的状态是200
					.andDo(print()) // 打印出请求和相应的内容
					.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串

			Date eDate = new Date();

			double second = (eDate.getTime() - bdate.getTime()) / 1000.0;
			logger.info("done! use " + second + " s");

		} catch (Exception e) {
			e.printStackTrace();
			// msg.setBol(false);
		}

	}

	// @Test
	public void uploadjpegTest() {

	}

}
