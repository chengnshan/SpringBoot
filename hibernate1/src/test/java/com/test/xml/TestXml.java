package com.test.xml;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.junit.Test;

import com.cxp.pojo.Student;
import com.cxp.pojo.User;
import com.cxp.pojo.wechat.ReplyTextMsg;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class TestXml {

	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对所有 xml 节点的转换都增加 CDATA 标记
				boolean cdata = true;

				@SuppressWarnings("rawtypes")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});

	@Test
	public void test1() throws IOException {
		ReplyTextMsg textMsg = new ReplyTextMsg();

		textMsg.setFromUserName("ToUserName");
		textMsg.setToUserName("FromUserName");
		textMsg.setMsgType("text");// 文本类型
		textMsg.setCreateTime("" + new Date().getTime());// 当前时间
		textMsg.setContent("您发送的消息是：" + "Content");// 返回消息
		xstream.alias("xml", ReplyTextMsg.class);
		String xml = xstream.toXML(textMsg);
		System.out.println(xml);
	}

	@Test
	public void test2() {
		// 解决XStream对出现双下划线的bug
		XStream xStream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
		User user = new User(1, "张四右", "1236@163.com", "123456");
		List<String> arrayList = new ArrayList<String>();
		arrayList.add("打球");
		arrayList.add("看书");
		user.setLoves(arrayList);
		System.out.println("----------------第1次输出, 设置类、字段别名---------------- \n");
		System.out.println(xStream.toXML(user));
		
		System.out.println("----------------第2次输出, 设置类、字段别名---------------- \n");
		//设置类别名，不设默认类全路径
		xStream.alias("user", User.class);
		System.out.println(xStream.toXML(user));
		
		System.out.println("----------------第3次输出, 设置类、字段别名---------------- \n");
		//设置字段别名
		xStream.aliasField("user_password", User.class, "password");
		xStream.aliasField("user_id", User.class, "id");
		System.out.println(xStream.toXML(user));
		
		System.out.println("----------------第4次输出, 设置类、字段别名---------------- \n");
		//设置类成员为xml一个元素上的属性 
		xStream.useAttributeFor(User.class, "name");
		//设置属性的别名 
		xStream.aliasAttribute(User.class, "name","user_name");
		System.out.println(xStream.toXML(user));
	}
	
	
	
	@Test
	public void test3() {
		String xmlStr1="<user user_name=\"张三丰\">\r\n" + 
				"  <user_id>1</user_id>\r\n" + 
				"  <email>1236@163.com</email>\r\n" + 
				"  <user_password>123456</user_password>\r\n" + 
				"  <loves>\r\n" + 
				"    <string>打球</string>\r\n" + 
				"    <string>看书</string>\r\n" + 
				"  </loves>\r\n" + 
				"</user>";
		// 解决XStream对出现双下划线的bug
		XStream xStream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
		xStream.alias("user", User.class);
		xStream.aliasField("user_password", User.class, "password");
		xStream.aliasField("user_id", User.class, "id");
		xStream.useAttributeFor(User.class, "name");
		xStream.aliasAttribute(User.class, "name","user_name");
		System.out.println((User)xStream.fromXML(xmlStr1));
	}
	
	@Test
	public void test4() {
		User user = new User(1, "张四右", "1236@163.com", "123456");
		List<String> arrayList = new ArrayList<String>();
		arrayList.add("打球");
		arrayList.add("看书");
		user.setLoves(arrayList);
		//删除根节点
	    XStream xStream = new XStream(new JsonHierarchicalStreamDriver() {
	    	@Override
	    	public HierarchicalStreamWriter createWriter(Writer out) {
	    		return new JsonWriter(out, JsonWriter.DROP_ROOT_MODE);
	    	}
	    });
	    xStream.setMode(XStream.NO_REFERENCES);
	    xStream.alias("user", User.class);
	    String xml = xStream.toXML(user);
	    System.out.println(xml);
	}
	
	@Test
	public void test5() {
		// 解决XStream对出现双下划线的bug
	    XStream xStream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
		Student stu = new Student("001", "李四的", new Date(), 22, "广东深圳");
		//将类上的注解将会使用,这句一定要加上，这句才能使注解生效
		xStream.processAnnotations(Student.class);
		System.out.println(xStream.toXML(stu));
	}
	
	static List validXPathList = new ArrayList<>();
	
	//递归算法：遍历配置文件，找出所有有效的xpath  
	private static void recursiveElement(Element element) {  
	    List<Element> elements = element.elements();  
	    validXPathList.add(element.getPath());  
	    if (elements.size() == 0) {  
	        //没有子元素  
	    } else {  
	        //有子元素  
	        for (Iterator<Element> it = elements.iterator(); it.hasNext();) {  
	            //递归遍历  
	            recursiveElement(it.next());  
	        }  
	    }  
	}  
	 
	//递归算法：遍历xml，标识无效的元素节点  
	private static void recursiveFixElement(Element element) {  
	    List<Element> elements = element.elements();  
	    if (!validXPathList.contains(element.getPath())) {  
	        element.addAttribute("delete", "true");  
	    }  
	    if (elements.size() == 0) {  
	        //没有子元素  
	    } else {  
	        //有子元素  
	        for (Iterator<Element> it = elements.iterator(); it.hasNext();) {  
	            Element e = it.next();  
	            if (!validXPathList.contains(e.getPath())) {  
	                e.addAttribute("delete", "true");  
	            }  
	            //递归遍历  
	            recursiveFixElement(e);  
	        }  
	    }  
	}  
	 
	/**  
	 * 过滤器接口方法，转换不规范字符，剔除无效节点  
	 *  
	 * @param xmlStr 要过滤的xml  
	 * @return 符合转换器要求的xml  
	 */
	public static String filter(String xmlStr) {  
	    Document document = null;  
	    try {  
	        document = DocumentHelper.parseText(xmlStr.replaceAll("__", "_"));  
	        //递归的调用：标记要剔除的xml元素  
	        recursiveFixElement(document.getRootElement());       
	        List<Node> nodeList = document.selectNodes("//@delete");  
	        for (Node node : nodeList) {  
	            node.getParent().detach();  //剔除xml元素  
	        }  
	    } catch (DocumentException e) {  
	        System.out.println(e.getMessage());  
	        e.printStackTrace();  
	    }  
	    return document.asXML();  
	} 
}
