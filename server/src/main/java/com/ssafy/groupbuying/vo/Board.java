package com.ssafy.groupbuying.vo;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Table(name="board")
@ApiModel
public @Data class Board {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	@NotNull
	@Column(length = 100)
//	@ApiModelProperty(value = "제목")
	private String title;
	@NotNull
	@Column(columnDefinition = "TEXT")
//	@ApiModelProperty(value = "내용")
	private String context;
//	@NotNull
	@Column(length = 20)
	private String locationX;
//	@NotNull
	@Column(length = 20)
	private String locationY;
	
	@CreationTimestamp
	private LocalDateTime  writeDate;
	// 일단 NOT NULL 제외
	private LocalDateTime deadlineDate;
	@NotNull
	@Max(5)
//	@ApiModelProperty(value = "제한 인원")
	private int limit_num;
	
	@ColumnDefault("1")
	private int participants;
	@NotNull
	@Column(columnDefinition = "boolean default true")
//	@ApiModelProperty(value = "카테고리")
	private int category;
	@Column(columnDefinition = "boolean default false")
	private boolean isDeleted;
	
	// TEST 용
	public Board() {}
	public Board(User user, @NotNull String title, @NotNull String context,
			@NotNull int limit_num, @NotNull int category) {
		super();
		this.user = user;
		this.title = title;
		this.context = context;
		this.limit_num = limit_num;
		this.category = category;
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

	public LocalDateTime getWriteDate() {
		return writeDate;
	}

	public LocalDateTime getDeadlineDate() {
		return deadlineDate;
	}

	public int getLimit_num() {
		return limit_num;
	}

	public int getCategory() {
		return category;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public void setLimit_num(int limit_num) {
		this.limit_num = limit_num;
	}
	

	

}
