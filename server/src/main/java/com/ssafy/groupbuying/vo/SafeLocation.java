package com.ssafy.groupbuying.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name="Safe_Location")
public @Data class SafeLocation {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@NotNull
	private int type;
	@NotNull
	private double latitude;
	@NotNull
	private double longitude;
	@Column(length = 30)
	private String name;
	@Column(length = 200)
	private String address;
	private int code;
	@Column(length = 5)
	private String line;
	
}
