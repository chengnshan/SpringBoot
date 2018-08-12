package com.cxp.dao.entityDao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cxp.dao.BaseDaoImpl;
import com.cxp.dao.entityDao.CusomerRepository;
import com.cxp.pojo.CustomerInfo;

@Repository(value = "cusomerRepository")
@Transactional
public class CusomerRepositoryImpl extends BaseDaoImpl<CustomerInfo> implements CusomerRepository {

}
