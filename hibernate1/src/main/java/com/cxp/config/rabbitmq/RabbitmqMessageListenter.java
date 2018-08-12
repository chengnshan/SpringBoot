package com.cxp.config.rabbitmq;

import java.io.IOException;

import com.cxp.pojo.CustomerInfo;
import com.cxp.util.JackJsonUtil;

public class RabbitmqMessageListenter {
	
	public void handleMessage(String customerInfo) throws IOException {
		
		if(null != customerInfo && JackJsonUtil.isJSONValid2(customerInfo)) {
			System.out.println(JackJsonUtil.jsonToObject(customerInfo, CustomerInfo.class));
		}else {
			System.out.println(customerInfo);
		}
		
	}
}
