package com.ssafy.groupbuying.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="Safe_Location")
public @Data class SafeLocation {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private int type;
	private int code;
	private int line;
	private double latitude;
	private double longitude;
	private String name;
	private String address;
	
}
