package com.ssafy.groupbuying.vo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
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
	
	@NotNull
	private String title;
	@NotNull
	private String context;
	@NotNull
	private String locationX;
	@NotNull
	private String locationY;
	
	@CreationTimestamp
	private LocalDateTime  writeDate;
	// 일단 NOT NULL 제외
	private LocalDateTime deadlineDate;
	@NotNull
	private int limit_num;
	
	@ColumnDefault("1")
	private int participants;
	@NotNull
	private int category;
	@Column(name = "isDeleted", columnDefinition = "boolean default true")
	private boolean isDeleted;
	
	// TEST 용
	public Board(User user, @NotNull String title, @NotNull String context,
			@NotNull int limit_num, @NotNull int category) {
		super();
		this.user = user;
		this.title = title;
		this.context = context;
		this.limit_num = limit_num;
		this.category = category;
	}


}
