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
	private long id;
	
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
	private int limit;
	private int participants;
	private int category;
	private boolean delete;

	public Board() {}

	public Board(long id, User user, String title, String context, String locationX, String locationY,
			String writeDate, String deadlineDate, int limit, int participants, int category, boolean delete) {
		super();
		this.id = id;
		this.user = user;
		this.title = title;
		this.context = context;
		this.locationX = locationX;
		this.locationY = locationY;
		this.writeDate = writeDate;
		this.deadlineDate = deadlineDate;
		this.limit = limit;
		this.participants = participants;
		this.category = category;
		this.delete = delete;
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
		return limit;
	}

	public int getParticipants() {
		return participants;
	}

	public int getCategory() {
		return category;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setId(long id) {
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

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void setParticipants(int participants) {
		this.participants = participants;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}
	

}
