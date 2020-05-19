package com.ssafy.groupbuying.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



import lombok.Data;

@Data
@Entity
public class Board {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	@ManyToOne
	@JoinColumn(name = "id")
	private User writer;
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
