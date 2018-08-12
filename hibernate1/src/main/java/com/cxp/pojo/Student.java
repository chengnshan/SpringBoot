package com.cxp.pojo;

import java.util.Date;

import com.test.config.XStreamYMDDateConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@XStreamAlias(value = "student")
public class Student {

	@XStreamAsAttribute
	private String id;

	@XStreamAlias(value = "stu_name")
	private String name;

	@XStreamAlias(value = "stu_birthday")
	@XStreamConverter(value = XStreamYMDDateConverter.class)
	private Date birthday;

	private int age;
	private String address;

	public Student() {
		super();
	}

	public Student(String id, String name, Date birthday, int age, String address) {
		super();
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.age = age;
		this.address = address;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", birthday=" + birthday + ", age=" + age + ", address="
				+ address + "]";
	}

}
