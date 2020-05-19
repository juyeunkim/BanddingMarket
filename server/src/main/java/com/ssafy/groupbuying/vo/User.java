package com.ssafy.groupbuying.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
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
	
	public User() {}
	
	
	

	public User(String id, String password, String role, String nickname, String name, int age,
			String gender, String phone, String locationX, String locationY, double reputation) {
		super();
		this.id = id;
		this.password = password;
		this.role = role;
		this.nickname = nickname;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.phone = phone;
		this.locationX = locationX;
		this.locationY = locationY;
		this.reputation = reputation;
	}





	public String getId() {
		return id;
	}






	public void setEmail(String id) {
		this.id = id;
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
	
	

}
