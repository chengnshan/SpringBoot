package com.test.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.test.pojo.Student;

public class GenericClass extends SuperClass<Student> {

	public static void main(String[] args) {
		GenericClass gen = new GenericClass();
	}

}

class SuperClass<T> {
	public SuperClass() {
		Type type = this.getClass().getGenericSuperclass();
		ParameterizedType ptype = (ParameterizedType) type;
		Class<T> clazz = (Class<T>) ptype.getActualTypeArguments()[0];
		System.out.println(clazz);
		
	}
}