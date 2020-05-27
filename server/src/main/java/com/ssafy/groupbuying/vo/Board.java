package com.ssafy.groupbuying.vo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="board")
@ApiModel
public @Data class Board {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long board_id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	
	@JoinColumn(name = "user_id", referencedColumnName="user_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name="user_id"))
//	@ManyToOne(cascade = CascadeType.PERSIST)
//	@JoinColumn(name = "user")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	@NotNull
	@Column(length = 100)
	private String title;
	@NotNull
	@Column(columnDefinition = "TEXT")
	private String context;
//	@NotNull
	@Column(length = 20)
	private String board_locationX;
 	@Column(length = 20)
	private String board_locationY;
	
	@CreationTimestamp
	private LocalDateTime  writeDate;
	// 일단 NOT NULL 제외
	private LocalDateTime deadlineDate;
	@NotNull
	@Max(5)
	private int limit_num;
	
	@ColumnDefault("1")
	private int participants;
	@NotNull
	@Column(columnDefinition = "boolean default true")
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
	
	
	public Board(long board_id, User user, @NotNull String title, @NotNull String context, String board_locationX,
			String board_locationY, LocalDateTime writeDate, LocalDateTime deadlineDate, @NotNull @Max(5) int limit_num,
			int participants, @NotNull int category, boolean isDeleted) {
		super();
		this.board_id = board_id;
		this.user = user;
		this.title = title;
		this.context = context;
		this.board_locationX = board_locationX;
		this.board_locationY = board_locationY;
		this.writeDate = writeDate;
		this.deadlineDate = deadlineDate;
		this.limit_num = limit_num;
		this.participants = participants;
		this.category = category;
		this.isDeleted = isDeleted;
	}

}
