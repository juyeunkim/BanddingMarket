package com.ssafy.groupbuying.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;

@Entity
@Table(name="board")
public @Data class Board {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@OneToOne
	@JoinColumn(name = "user")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	private String title;
	private String context;
	private String locationX;
	private String locationY;
	private String writeDate;
	private String deadlineDate;
	private int limit_num;
	private int participants;
	private int category;
	private boolean Isdelete;


}
