package com.ssafy.groupbuying.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;

@Entity
@Table(name="report")
public @Data class Report {
	@Id
	private String id;
	
	@OneToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name= "writer") // 실제 DB의 컬럼 이름
	private User writer;
	@OneToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name= "reported")
	private User reported;
	@NotNull
	private String context;
	@NotNull
	private String category;
	@NotNull
	private int status;
}
