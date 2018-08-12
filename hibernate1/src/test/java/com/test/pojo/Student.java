package com.test.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.test.config.MyDateDeSerializer;
import com.test.config.MyDateSerializer;
import com.test.pojo.abstra.Lion;

public class Student implements Serializable {

	private static final long serialVersionUID = -3919874308341658424L;

	private int id;
	private String name;
	private String address;
	@JsonSerialize(using = MyDateSerializer.class)
	@JsonDeserialize(using = MyDateDeSerializer.class)
	private Date birthday;
	@JsonIgnore
	private Lion lion;
	private List<String> lists;

	public List<String> getLists() {
		return lists;
	}

	public void setLists(List<String> lists) {
		this.lists = lists;
	}

	public Lion getLion() {
		return lion;
	}

	public void setLion(Lion lion) {
		this.lion = lion;
	}

	public Student() {
		super();
	}

	public Student(int id, String name, String address, Date birthday) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.birthday = birthday;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", address=" + address + ", birthday=" + birthday + ", lists="
				+ lists + "]";
	}

}
