package com.ssafy.groupbuying.vo;

import javax.persistence.Column;
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
@Table(name="comment")
public class Comment {
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
	
	@Column(name="context")
	private String context;
	
	public Comment() {}

	public Comment(int id, Board board, User user, String context) {
		super();
		this.id = id;
		this.board = board;
		this.user = user;
		this.context = context;
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

	public String getContext() {
		return context;
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

	public void setContext(String context) {
		this.context = context;
	}
	
	
	
	
}
