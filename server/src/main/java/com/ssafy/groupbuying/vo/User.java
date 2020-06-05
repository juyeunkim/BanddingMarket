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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="user")
public @Data class User {
	@Id @GeneratedValue( strategy =GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private long user_id;
	@NotNull
	@Column(length = 30)
	@UniqueElements
	private String email;
	@NotNull
	@Column(length = 20)
	private String password;
	@Column(length = 6)
	@NotNull
	@ColumnDefault("'user'")
	private String role;
	@Column(length = 20, unique=true)
	@NotNull
	private String nickname;
	@Column(length = 20)
	@NotNull
	private String name;
	private Integer age;
	@Column(length = 1)
	private String gender;
	@Column(length = 20, unique=true)
	private String phone;
	@Column(length = 20)
	private String user_locationX;
	@Column(length = 20)
	private String user_locationY;
	
	@ColumnDefault("0")
	@NotNull
	private double reputation;
	
	
}
