package com.cxp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxp.dao.entityDao.CusomerRepository;
import com.cxp.pojo.CustomerInfo;
import com.cxp.pojo.User;
import com.cxp.util.JackJsonUtil;
import com.cxp.view.ViewExcel;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class CustomerController {

	@Autowired
	private CusomerRepository cusomerRepository;

	@RequestMapping(value = "/findCustomerInfo")
	@ResponseBody
	public String findCustomerInfo(HttpServletRequest request, String name, String address,String callback) {
		System.out.println("findCustomerInfo : " + name + "   address   " + address);
		List<CustomerInfo> findAll = cusomerRepository.findAll();
		String jsonString = null;
		try {
			jsonString = JackJsonUtil.objectToString(findAll);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println(jsonString);
		
		CustomerInfo customerInfo = cusomerRepository.getOneByProperty("id", 15);
		List<CustomerInfo> listByProperty = cusomerRepository.getListByProperty("name", "张三丰");
		System.out.println(customerInfo);
		System.out.println(listByProperty);
		
		return callback == null ? jsonString :(callback+"("+jsonString+")");
	}

	@RequestMapping(value = "/saveCustomerInfo")
	public ModelAndView saveCustomerInfo(ModelAndView modelAndView, @RequestBody CustomerInfo customerInfo) {
		System.out.println(customerInfo);
		customerInfo.setBirthday(new Date());
		// customerInfo.setCreateDate(new Date());
		Serializable save = cusomerRepository.save(customerInfo);
		System.out.println(save);
		List<CustomerInfo> findAll = cusomerRepository.findAll();
		modelAndView.addObject("findAll", findAll);
		modelAndView.setViewName("i18n/i18n_1");
		return modelAndView;
	}

	@Autowired
	private ResourceBundleMessageSource messageSource;

	@RequestMapping("/i18n")
	public String testI18N(Locale locale) {
		String val = messageSource.getMessage("i18n.username", null, locale);
		System.out.println(val);
		return "i18n/i18n_1";
	}

	@RequestMapping("/testMyView")
	public String testMyView(ModelAndView modelAndView) {
		System.out.println("helloView");
		CustomerInfo customerInfo = cusomerRepository.get(12);
		customerInfo.setName("程若媚");
		cusomerRepository.saveOrUpdate(customerInfo);
		return "helloView";
	}

	@RequestMapping(value = "excel")
	public String viewExcel(ModelMap map) {
		List<User> userList = new ArrayList<>();
		User user = new User(1, "Tome", "Tom@qq.com", "123456");
		userList.add(user);
		map.addAttribute("userList", userList);
//		return new ModelAndView(new ViewExcel(), map);
		return "excel";
	}
	
	@RequestMapping(value = "pdf")
	public String viewPDF(ModelMap map) {
		List<User> userList = new ArrayList<>();
		User user = new User(1, "Tome", "Tom@qq.com", "123456");
		userList.add(user);
		map.addAttribute("userList", userList);
//		return new ModelAndView(new ViewPDF(), map);
		return "pdf";
	}

}

