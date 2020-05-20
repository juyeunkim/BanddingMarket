package com.ssafy.groupbuying.vo;

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
	private String name;
	private String address;
	private int code;
	private String line;
	
}
