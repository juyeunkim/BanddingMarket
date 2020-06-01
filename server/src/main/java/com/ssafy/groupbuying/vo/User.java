package com.ssafy.groupbuying.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="user")
public @Data class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long user_id;
	
	@NotNull
	@Column(length = 30, unique = true)
	private String email;
	
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
	@Column(length = 20)
	private String name;
	
	@ColumnDefault("0")
	private int age;
	@Column(length = 1)
	private String gender;
	@Column(unique = true, length = 20)
	private String phone;
	@Column(length = 20)
	private String user_locationX;
	@Column(length = 20)
	private String user_locationY;
	
	@ColumnDefault("0")
	private double reputation;
	
	public User() {}

	// TEST 용
	public User(String email, @NotNull String password, @NotNull String role, @NotNull String nickname,
			@NotNull String name, int age, String gender, String phone) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
		this.nickname = nickname;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.phone = phone;
	}
	
	
}
