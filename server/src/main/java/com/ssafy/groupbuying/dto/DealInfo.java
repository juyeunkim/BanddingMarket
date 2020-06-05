package com.ssafy.groupbuying.dto;

import java.io.Serializable;

import lombok.Data;

public @Data class DealInfo implements Serializable {
	
	private int category;
	private double latitude;
	private double longitude;
	private int dist;
	
	public DealInfo() {}
	public DealInfo(int dist, double latitude, double longitude) {
		super();
		this.dist = dist;
		this.latitude = latitude;
		this.longitude = longitude;
	}
}
