package com.ssafy.groupbuying.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.UniqueElements;

import lombok.Data;

@Entity
@Table(name="user")
@Data
public  class User {
	@Id @GeneratedValue( strategy =GenerationType.AUTO)
	private Long id;
	@NotNull
	@Column(length = 30)
	@UniqueElements
	private String mail;
	@NotNull
	@Column(length = 20)
	private String password;
	@Column(length = 6)
	@ColumnDefault("'user'")
	private String role;
	@Column(length = 20, unique=true)
	@NotNull
	private String nickname;
	@Column(length = 20)
	@NotNull
	private String name;
	@ColumnDefault("0")
	private Integer age;
	@Column(length = 1)
	private String gender;
	@Column(length = 20, unique=true)
	private String phone;
	@Column(name="location_x", length = 20)
	private String locationX;
	@Column(name="location_y",length = 20)
	private String locationY;
	
	@ColumnDefault("0")
	private Double reputation;
	
	// TEST 용
	
	
	
}
