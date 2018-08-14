package com.cxp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.cxp.dao.UserInfoRepository;
import com.cxp.pojo.UserInfo;
import com.cxp.service.UserInfoService;

@Service(value = "userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoRepository UserInfoRepository;

	@Override
	public List<UserInfo> findUserInfoList() {
		List<UserInfo> findAll = UserInfoRepository.findAll();
		return findAll;
	}

//	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public UserInfo insertUserInfo(UserInfo userInfo) {
		UserInfo save = UserInfoRepository.save(userInfo);
		return save;
	}

	@Override
	public UserInfo updateUserInfo(UserInfo userInfo) {
		UserInfoRepository.saveAndFlush(userInfo);
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor= {Exception.class})
	@Override
	public void saveAndUpdateUserInfo() throws Exception {
		
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		
		UserInfo userInfo1 = new UserInfo();
		userInfo1.setUserName("增加了");
		this.insertUserInfo(userInfo1);
		
		
		UserInfo findOne = UserInfoRepository.findOne(2);
		findOne.setUserName("李四点四111");
		updateUserInfo(findOne);
		
		throw new Exception();
	}

}
