package com.ssafy.groupbuying.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;

@Entity
@Table(name="user")
public @Data class User {
	@Id
	@Column(length = 30)
	private String id;
	
	@NotNull
	@Column(length = 20)
	private String password;
	@NotNull
	@Column(length = 6)
	private String role;
	@NotNull
	@Column(length = 20)
	private String nickname;
	@NotNull
	private String name;
	private int age;
	@Column(length = 1)
	private String gender;
	@Column(unique = true, length = 20)
	private String phone;
	@Column(length = 20)
	private String locationX;
	@Column(length = 20)
	private String locationY;
	
	@ColumnDefault("0")
	private double reputation;
	
	// TEST 용
	public User() {}
	public User(String id, @NotNull String password, @NotNull String role, @NotNull String nickname,
			@NotNull String name, int age, String gender, String phone) {
		super();
		this.id = id;
		this.password = password;
		this.role = role;
		this.nickname = nickname;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.phone = phone;
	}
	
	
}
