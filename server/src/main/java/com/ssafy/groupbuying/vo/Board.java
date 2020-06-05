package com.ssafy.groupbuying.vo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

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
	@JoinColumn(name = "user_id")
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
	@Max(5)
	@Column(columnDefinition = "integer default 5")
	private int limit_num;
	
	@Column(columnDefinition = "integer default 1")
	private int participants;
	@NotNull
	@Column(columnDefinition = "boolean default true")
	private int category;
	@Column(columnDefinition = "boolean default false")
	private boolean isDeleted;
	
//	//카테고리용 컬럼 
//	@Column(length = 50)
//	private String category;
	
	//키워드용 컬럼 
	@Column(length = 50)
	private String keyword;
	
	
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
	
	

}
