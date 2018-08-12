package com.cxp.controller;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cxp.util.wechat.MessageUtil;
import com.cxp.util.wechat.SignUtil;

@Controller
@RequestMapping("/wechat")
public class WeiXinController {

	private static Logger logger = Logger.getLogger(WeiXinController.class);

	@RequestMapping(value = "/security")
	public String testChat(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "signature", required = true) String signature,
			@RequestParam(value = "timestamp", required = true) String timestamp,
			@RequestParam(value = "nonce", required = true) String nonce,
			@RequestParam(value = "echostr", required = true) String echostr) {
		System.out.println("signature : " + signature + " ,timestamp :" + timestamp + ", nonce : " + nonce
				+ " , echostr : " + echostr);
		try {
			if (SignUtil.checkSignature(signature, timestamp, nonce)) {
				PrintWriter out = response.getWriter();
				out.print(echostr);
				out.close();
			} else {
				logger.info("这里存在非法请求！");
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return null;
	}

	@RequestMapping(value = "security", method = RequestMethod.POST)
	// post 方法用于接收微信服务端消息
	public void DoPost(HttpServletRequest request, HttpServletResponse response) {
		// 防止中文乱码
		response.setCharacterEncoding("UTF-8");
		System.out.println("这是 post 方法！");
		try {

			 Map<String, String> map = MessageUtil.parseXml(request);
			 System.out.println("=============================" + map.get("Content"));
			 MessageUtil.replyMessage(response.getWriter(), map);
		} catch (Exception e) {
			logger.error(e, e);
		}
	}
}
