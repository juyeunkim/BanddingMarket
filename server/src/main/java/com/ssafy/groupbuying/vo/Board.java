package com.ssafy.groupbuying.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Board {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String userId;
	private String title;
	private String context;
	private String locationX;
	private String locationY;
	private String writeDate;
	private String deadlineDate;
	private int limit;
	private int participants;
	private int category;
	private boolean delete;
}
