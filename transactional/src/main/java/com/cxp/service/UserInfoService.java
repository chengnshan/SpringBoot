package com.cxp.service;

import java.util.List;

import com.cxp.pojo.UserInfo;

public interface UserInfoService {

	public List<UserInfo> findUserInfoList();
	
	public UserInfo insertUserInfo(UserInfo userInfo);
	
	public UserInfo updateUserInfo(UserInfo userInfo);
	
	public void saveAndUpdateUserInfo() throws Exception;
}
