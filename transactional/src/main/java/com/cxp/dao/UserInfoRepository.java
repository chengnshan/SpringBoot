package com.cxp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cxp.pojo.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer>{

}
