package com.test.pojo.abstra;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Lion extends Animal {
	private String name;
	private double price;

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	// @JsonCreator
	public Lion(@JsonProperty("name") String name, @JsonProperty("price") double price) {
		this.name = name;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Lion [name=" + name + ", price=" + price + "]";
	}

}
