package com.ztgm.mall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ztgm.mall.pojo.Role;
import com.ztgm.mall.service.RoleService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Rollback(false)
public class ZtgmiotApplicationTests {

	
	@Autowired
	private RoleService roleService;
	
	@Transactional
	@Test
	public void contextLoads() throws Exception {
		
		try {
			roleService.insertSelective(new Role("101","101","1"),"");
			//roleService.insert(new Role("102","102","1"));
			/*if(true)
			throw new Exception();*/
			//roleService.insert(new Role("103","103","1"));
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
	
	}

}
