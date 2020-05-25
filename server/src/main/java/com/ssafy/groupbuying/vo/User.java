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

@Data
@Table(name="user")
public  class User {
	@Id @GeneratedValue( strategy =GenerationType.AUTO)
	private Long id;
	
	private String password;	
	private String role;	
	private String nickname;	
	private String name;
	@ColumnDefault("0")
	private Integer age;
	private String gender;
	
	private String phone;
	private String locationX;
	private String locationY;
	
	@ColumnDefault("0")
	private Double reputation;
	
	// TEST 용
	
	
	
}
