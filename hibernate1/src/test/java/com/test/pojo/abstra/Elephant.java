package com.test.pojo.abstra;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Elephant extends Animal {
	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

    @JsonCreator
	public Elephant(@JsonProperty("name") String name,@JsonProperty("age") int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Elephant [name=" + name + ", age=" + age + "]";
	}


}
