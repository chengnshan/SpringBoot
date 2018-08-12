package com.test.main;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import com.cxp.util.DOMUtils;

public class TestXml {

	@Test
	public void test1() {
		Document document = null;
		String xmlStr = "<user user_name=\"张四右\">\r\n" + "  <user_id>1</user_id>\r\n"
				+ "  <email>1236@163.com</email>\r\n" + "  <user_password>123456</user_password>\r\n" + "  <loves>\r\n"
				+ "    <string>打球</string>\r\n" + "    <string>看书</string>\r\n" + "  </loves>\r\n" + "</user>";
		try {
			// 将字符串转为XML
			document = DocumentHelper.parseText(xmlStr);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		//获取根元素
		Element rootElement = document.getRootElement();
		//遍历子节点
		for (Iterator i = rootElement.elementIterator(); i.hasNext();) {
			Element el = (Element) i.next();
			System.out.println(el.getName());
		}
		//获取当前元素下的全部子元素
		List<Element> elements = rootElement.elements();
		//获取属性列表
		Attribute attribute = rootElement.attribute("user_name");
		String value = attribute.getValue();
		System.out.println(value);
		Element childElement = DOMUtils.getChildElement(rootElement, "user_password");
		System.out.println(childElement.getText());
	}

	@Test
	public void test2() {
		Document document = null;
		// 从xml文件获取数据
		SAXReader saxReader = new SAXReader();
		try {
			document = saxReader.read(new File(""));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}

