package com.cxp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cxp.pojo.UserInfo;
import com.cxp.service.UserInfoService;

@Controller
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping(value="/findUserInfoList")
	@ResponseBody
	public List<UserInfo> findUserInfoList(){
		try {
			
			userInfoService.saveAndUpdateUserInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userInfoService.findUserInfoList();
	}
	
	@RequestMapping(value="/insertUserInfoList")
	@ResponseBody
	public UserInfo insertUserInfoList(UserInfo userInfo){
		
		return userInfoService.insertUserInfo(userInfo);
	}
}
