package com.test.pojo;

import java.util.List;

public class MsgInfo {

	private String templateCode;
	private String title;
	private List<Object> cotent;

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Object> getCotent() {
		return cotent;
	}

	public void setCotent(List<Object> cotent) {
		this.cotent = cotent;
	}

	@Override
	public String toString() {
		return "MsgInfo [templateCode=" + templateCode + ", title=" + title + ", cotent=" + cotent + "]";
	}

}
