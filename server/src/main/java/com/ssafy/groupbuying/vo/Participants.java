package com.ssafy.groupbuying.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;


@Entity
@Table(name="participants")
public @Data class Participants {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name= "board")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Board board;
	
	@OneToOne
	@JoinColumn(name= "user")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	public int getId() {
		return id;
	}
	
}
