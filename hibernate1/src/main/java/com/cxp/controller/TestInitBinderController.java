package com.cxp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cxp.pojo.CustomerInfo;
import com.cxp.pojo.User;

@SessionAttributes(value = { "user1,user" })
@Controller
public class TestInitBinderController {

	@RequestMapping("/testInitBinder")
	public String testInitBinder(Date birthday, User user, CustomerInfo customerInfo) {
		System.out.println("然后执行的方法");
		System.out.println("testInitBinder  birthday  : " + birthday);
		System.out.println("testInitBinder  user  : " + user);
		System.out.println("testInitBinder  customerInfo  : " + customerInfo);
		return "i18n/i18n_1";
	}

	@InitBinder("user")
	public void initBinder_1(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		binder.setFieldDefaultPrefix("user.");
	}

	@InitBinder("customerInfo")
	public void initBinder_2(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("cust.");
	}

	@InitBinder
	public void initBinder_date(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value = "modelTest.do")
	public String modelTest(User user, CustomerInfo customerInfo) {
		System.out.println(user);
		System.out.println(customerInfo);
		System.out.println("进入modelTest方法");
		return "modelTest";
	}

	@ModelAttribute("user")
	public User init(Model mode) {
		User user = new User(1, "小明", "男", "132");
		CustomerInfo cust = new CustomerInfo("003", "张三", "13333333");
		mode.addAttribute("customerInfo", cust);
		System.out.println("最先执行的方法");
		return user;
	}

	@ModelAttribute
	public void init02() {
		System.out.println("最先执行的方法02");
	}

	@RequestMapping(value = "modelTestParam.do")
	public String modelTestParam(@ModelAttribute("user") User user,
			@ModelAttribute("MyCustomerInfo") CustomerInfo customerInfo) {
		user.setName("赵直接");
		user.setEmail("abc@163.com");
		customerInfo.setBirthday(new Date());
		customerInfo.setCustNo("110");
		return "modelTest";
	}

	@RequestMapping(value = "hello4", method = { RequestMethod.GET, RequestMethod.POST })
	public String doHello4(@ModelAttribute("user1") User user,
			Map<String, Object> map, HttpSession session) {

		System.out.println(map.get("user1"));
		System.out.println("session:" + session.getAttribute("user1"));

		return "modelTest";
	}
}
