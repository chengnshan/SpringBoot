package com.cxp.util.wechat;

import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.cxp.pojo.wechat.Article;
import com.cxp.pojo.wechat.Image;
import com.cxp.pojo.wechat.ImageMsg;
import com.cxp.pojo.wechat.NewsMessage;
import com.cxp.pojo.wechat.ReplyTextMsg;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * ClassName: MessageUtil
 * @Description: 消息工具类
 */
public class MessageUtil {

	/**
	 * 返回消息类型：文本
	 */
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 返回消息类型：音乐
	 */
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

	/**
	 * 返回消息类型：图文
	 */
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";

	/**
	 * 请求消息类型：文本
	 */
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 请求消息类型：图片
	 */
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

	/**
	 * 请求消息类型：链接
	 */
	public static final String REQ_MESSAGE_TYPE_LINK = "link";

	/**
	 * 请求消息类型：地理位置
	 */
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

	/**
	 * 请求消息类型：音频
	 */
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

	/**
	 * 请求消息类型：推送
	 */
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";

	/**
	 * 事件类型：subscribe(订阅)
	 */
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

	/**
	 * 事件类型：unsubscribe(取消订阅)
	 */
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

	/**
	 * 事件类型：CLICK(自定义菜单点击事件)
	 */
	public static final String EVENT_TYPE_CLICK = "CLICK";

	/**
	 * @Description: 解析微信发来的请求（XML）
	 * @param @param request
	 * @param @return
	 * @param @throws Exception
	 * @author dapengniao
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
		// 将解析结果存储在 HashMap 中
		Map<String, String> map = new HashMap<String, String>();
		// 从 request 中取得输入流
		InputStream inputStream = request.getInputStream();
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		// 得到 xml 根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();
		// 遍历所有子节点
		for (Element e : elementList)
			map.put(e.getName(), e.getText());

		// 释放资源
		inputStream.close();
		inputStream = null;
		return map;
	}

	@SuppressWarnings("unused")
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
	
	 /**
     * @Description: 文本消息对象转换成 xml
     * @param @param textMessage
     * @param @return
     */
    public static String textMessageToXml(ReplyTextMsg textMessage) {
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }
    
    /**
     * @Description: 图片消息对象转换成 xml
     * @param @param imageMessage
     * @param @return
     */
    public static String imageMessageToXml(ImageMsg imageMessage) {
        xstream.alias("xml", imageMessage.getClass());
        return xstream.toXML(imageMessage);
    }

    /**
     * @Description: 图文消息对象转换成 xml
     * @param @param newsMessage
     * @param @return
     */
    public static String newsMessageToXml(NewsMessage newsMessage) {
        xstream.alias("xml", newsMessage.getClass());
        xstream.alias("item", new Article().getClass());
        return xstream.toXML(newsMessage);
    }

	/**
	 * 返回一个消息
	 * @param out
	 * @param map
	 */
	public static void replyMessage( PrintWriter out,Map<String,String> map) {
		String MsgType = map.get("MsgType");
		//判断是否为文本消息
		if ("text".equals(MsgType)) {
			ReplyTextMsg textMsg = new ReplyTextMsg();
			
			textMsg.setFromUserName(map.get("ToUserName"));
			textMsg.setToUserName(map.get("FromUserName"));
			textMsg.setMsgType(RESP_MESSAGE_TYPE_TEXT);//文本类型
			textMsg.setCreateTime("" + new Date().getTime());//当前时间
			textMsg.setContent("黄老板,你发送的消息是：" + map.get("Content"));//返回消息
			try {
				// 将对象转化为XML
				String replyMsg = textMsg.Msg2Xml();
				out.println(replyMsg);
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("image".equals(MsgType)) {
			//将发过来的图片转发回去
            ImageMsg ImageMsg=new ImageMsg();
            ImageMsg.setFromUserName(map.get("ToUserName"));
            ImageMsg.setToUserName(map.get("FromUserName"));
            ImageMsg.setCreateTime("" + new Date().getTime());
            ImageMsg.setMsgType(REQ_MESSAGE_TYPE_IMAGE);
            Image image=new Image();
            image.setMediaId(map.get("MediaId"));
            ImageMsg.setImage(image);
            try {
                String replyMsg = ImageMsg.Msg2Xml();
                System.out.println("replyMsg："+replyMsg);
                out.println(replyMsg);
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
	}
}
