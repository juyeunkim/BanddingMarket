package com.ssafy.groupbuying.dto;

import java.io.Serializable;

import lombok.Data;

public @Data class DealInfo implements Serializable {
	
	private int type;
	private double latitude;
	private double longitude;
	private int dist;
	
}
