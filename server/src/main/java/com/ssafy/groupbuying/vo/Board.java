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

@Entity
@Table(name="board")
public class Board {
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

	public Board() {}

	public Board(int id, User user, String title, String context, String locationX, String locationY,
			String writeDate, String deadlineDate, int limit_num, int participants, int category, boolean Isdelete) {
		super();
		this.id = id;
		this.user = user;
		this.title = title;
		this.context = context;
		this.locationX = locationX;
		this.locationY = locationY;
		this.writeDate = writeDate;
		this.deadlineDate = deadlineDate;
		this.limit_num = limit_num;
		this.participants = participants;
		this.category = category;
		this.Isdelete = Isdelete;
	}

	public long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public String getTitle() {
		return title;
	}

	public String getContext() {
		return context;
	}

	public String getLocationX() {
		return locationX;
	}

	public String getLocationY() {
		return locationY;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public String getDeadlineDate() {
		return deadlineDate;
	}

	public int getLimit() {
		return limit_num;
	}

	public int getParticipants() {
		return participants;
	}

	public int getCategory() {
		return category;
	}

	public boolean isDelete() {
		return Isdelete;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setWriter(User user) {
		this.user = user;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public void setLocationX(String locationX) {
		this.locationX = locationX;
	}

	public void setLocationY(String locationY) {
		this.locationY = locationY;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public void setDeadlineDate(String deadlineDate) {
		this.deadlineDate = deadlineDate;
	}

	public void setLimit(int limit_num) {
		this.limit_num = limit_num;
	}

	public void setParticipants(int participants) {
		this.participants = participants;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public void setDelete(boolean Isdelete) {
		this.Isdelete = Isdelete;
	}
	

}
