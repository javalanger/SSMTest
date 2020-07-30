package com.jykj.po;
// 用户信息
public class User {
	private Integer id; // id
	private Integer gender; // 性别
	private String name; // 用户姓名
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", gender=" + gender + ", name=" + name + "]";
	}
	
}
