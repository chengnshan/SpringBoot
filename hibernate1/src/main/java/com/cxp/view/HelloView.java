package com.cxp.view;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

public class HelloView implements View{

	/**
	 * 获得视图的类型
	 */
	@Override
	public String getContentType() {
		return "text/html";
	}

	/**
	 * 对视图进行渲染
	 */
	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.getWriter().print("  HelloView   "+new Date());
	}

}
