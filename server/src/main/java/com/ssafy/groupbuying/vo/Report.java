package com.ssafy.groupbuying.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;

@Entity
@Table(name="report")
public @Data class Report {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@OneToOne
	@JoinColumn(name= "writer") // 실제 DB의 컬럼 이름
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User writer;
	
	@OneToOne
	@JoinColumn(name= "reported") 
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User reported;
	@NotNull
	@Column(columnDefinition = "TEXT")
	private String context;
	@NotNull
	@Column(length = 20)
	private String category;
	@NotNull
	@ColumnDefault("0") // 0 - 미확인 1- 승인 2- 삭제(거절)
	private int status;
}
