package com.ssafy.groupbuying.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="user")
public @Data class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Column(length = 30)
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
	private String locationX;
	@Column(length = 20)
	private String locationY;
	
	@ColumnDefault("0")
	private double reputation;
	
	// TEST 용
	public User() {}
	

	
	public long getId() {
		return id;
	}



	public String getEmail() {
		return email;
	}



	public String getPassword() {
		return password;
	}



	public String getRole() {
		return role;
	}



	public String getNickname() {
		return nickname;
	}



	public String getName() {
		return name;
	}



	public int getAge() {
		return age;
	}



	public String getGender() {
		return gender;
	}



	public String getPhone() {
		return phone;
	}



	public String getLocationX() {
		return locationX;
	}



	public String getLocationY() {
		return locationY;
	}



	public double getReputation() {
		return reputation;
	}



	public void setId(long id) {
		this.id = id;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public void setRole(String role) {
		this.role = role;
	}



	public void setNickname(String nickname) {
		this.nickname = nickname;
	}



	public void setName(String name) {
		this.name = name;
	}



	public void setAge(int age) {
		this.age = age;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public void setLocationX(String locationX) {
		this.locationX = locationX;
	}



	public void setLocationY(String locationY) {
		this.locationY = locationY;
	}



	public void setReputation(double reputation) {
		this.reputation = reputation;
	}



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
