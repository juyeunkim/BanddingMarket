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

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="Safe_Location")
public @Data class SafeLocation {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

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
	@ColumnDefault("0")
	private int code;
	@Column(length = 5)
	private String line;
	
}
