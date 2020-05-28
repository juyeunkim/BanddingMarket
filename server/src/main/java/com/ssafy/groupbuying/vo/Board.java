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
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="board")
@ApiModel
public @Data class Board {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user")
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

	

}
