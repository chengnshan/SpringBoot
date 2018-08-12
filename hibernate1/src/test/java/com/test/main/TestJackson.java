package com.test.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.pojo.MsgInfo;
import com.test.pojo.Student;
import com.test.pojo.abstra.Animal;
import com.test.pojo.abstra.Elephant;
import com.test.pojo.abstra.Lion;
import com.test.pojo.abstra.Zoo;
import com.cxp.util.JackJsonUtil;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

public class TestJackson {

	@Test
	public void test1() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		Student student = new Student(12, "张三丰", "湖北武汉", new Date());
		Lion lion = new Lion("Samba", 12000);
		student.setLion(lion);
		objectMapper
		.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
		String jsonStr = objectMapper.writeValueAsString(student);
		System.out.println(jsonStr);
	}

	@Test
	public void test2() throws IOException {
		String jsonStr = "{\"id\":12,\"name\":\"李世民\",\"address\":\"湖北武汉\",\"birthday\":\"2018-06-08 07:11:40\""
				+ ",\"age\":\"22\",\"lists\":[\"name\",\"小猫\",\"price\",\"250\"]}";
	/*	ObjectMapper mapper = new ObjectMapper();
		// 当反序列化json时，未知属性会引起的反序列化被打断，这里我们禁用未知属性打断反序列化功能，
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		Student readValue = mapper.readValue(jsonStr, Student.class);
		System.out.println(readValue);*/
		
		Student student = JackJsonUtil.jsonToObject(jsonStr, Student.class);
		System.out.println(student);
	}

	@Test
	public void test3() throws IOException {
		Zoo zoo = new Zoo("SH Wild Park", "ShangHai");
		Lion lion = new Lion("Samba", 12000);
		Elephant elephant = new Elephant("Manny", 29);
		List<Animal> animals = new ArrayList<Animal>();
		animals.add(lion);
		animals.add(elephant);
		zoo.setAnimals(animals);

		ObjectMapper mapper = new ObjectMapper();

		// mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		// mapper.writeValue(new File("zoo.json"), zoo);

		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		Zoo zoo1 = mapper.readValue(new File("zoo.json"), Zoo.class);
		System.out.println(zoo1);
	}
	
	@Test
	public void test4() throws Exception {
		Zoo zoo = new Zoo("SH Wild Park", "ShangHai");
		Map map = new HashMap<>();
		map.put("name", "SH Wild Park");
		map.put("city", "ShangHai");
		Zoo mapToObject = JackJsonUtil.MapToObject(map,Zoo.class);
		System.out.println(mapToObject);
		
		String str1="{\"city\":\"ShangHai\",\"name\":\"SH Wild Park\"}";
		System.out.println(JackJsonUtil.jsonToMap(str1));
	}
	
	@Test
	public void test5() throws IOException {
		String jsonStr = "{\"templateCode\":\"BBGGTT-GG\",\"title\":\"模板\",\"cotent\":[123,\"abc\"]}";
		List<Object> arrayList = new ArrayList<Object>();
		arrayList.add(123);
		arrayList.add("abc");
		String listStr = JackJsonUtil.objectToString(arrayList);
		System.out.println(listStr);
		String str1 = String.format("{\"templateCode\":\"%s\",\"title\":\"%s\",\"cotent\":%s}"
				, "BBGGTT-GG","模板",listStr);
		System.out.println(str1);
		MsgInfo msgInfo = JackJsonUtil.jsonToObject(jsonStr, MsgInfo.class);
		System.out.println(msgInfo);
		
		MsgInfo msgInfo1 = JackJsonUtil.jsonToObject(str1, MsgInfo.class);
		System.out.println(msgInfo1);
	}
}
