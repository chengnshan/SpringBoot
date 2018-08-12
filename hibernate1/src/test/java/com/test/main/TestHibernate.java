package com.test.main;

import java.util.List;

import javax.servlet.ServletContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.cxp.controller.CustomerController;
import com.cxp.dao.entityDao.CusomerRepository;
import com.cxp.pojo.CustomerInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration // 调用javaWEB的组件，比如自动注入ServletContext Bean等等
@ContextConfiguration(value = { "classpath:spring/applicationContext*.xml", "classpath:spring/springMVC.xml" })
public class TestHibernate {
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	ServletContext context;
	
	@Autowired
	CustomerController customerController;
	
	@Autowired
	private CusomerRepository cusomerRepository;

	MockMvc mockMvc;

	@Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(webApplicationContext).build();
    }
	
	@Test
	public void test1() throws Exception {
		MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
		params.add("name", "李四");
		params.add("address", "广东深圳");
		params.add("callback", "jcall");
		ResultActions perform = this.mockMvc.perform(MockMvcRequestBuilders.post("/findCustomerInfo")
				.accept(MediaType.APPLICATION_FORM_URLENCODED)
				.param("name", "李四")
				.param("address", "广东深圳")
				.param("callback", "jcall"));
		String contentAsString = perform.andReturn().getResponse().getContentAsString();
		System.out.println("contentAsString"+contentAsString);
		
		System.out.println(customerController.getClass().getName());
		
		CustomerInfo customerInfo = cusomerRepository.getOneByProperty("id", 15);
		System.out.println(customerInfo);
	}
}
