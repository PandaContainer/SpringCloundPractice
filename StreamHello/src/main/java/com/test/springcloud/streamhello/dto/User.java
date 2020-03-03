package com.test.springcloud.streamhello.dto;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	public User() {
	}

	public User(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	private String name;
	
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + "]";
	}
}
