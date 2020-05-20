package com.ssafy.groupbuying.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="user")
public @Data class User {
	@Id
	private String id;
	
	private String password;
	private String role;
	private String nickname;
	private String name;
	private int age;
	private String gender;
	private String phone;
	private String locationX;
	private String locationY;
	private double reputation;
	

}
