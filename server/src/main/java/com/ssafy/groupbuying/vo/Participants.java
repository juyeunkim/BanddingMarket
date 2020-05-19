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


@Entity
@Table(name="participants")
public class Participants {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name= "board")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Board board;
	
	@OneToOne
	@JoinColumn(name= "user")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	public Participants() {}

	public Participants(int id, Board board, User user) {
		super();
		this.id = id;
		this.board = board;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public Board getBoard() {
		return board;
	}

	public User getUser() {
		return user;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	
	
	
	
}
