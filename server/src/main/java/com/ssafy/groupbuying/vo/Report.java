package com.ssafy.groupbuying.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="report")
public @Data class Report {
	@Id
	private String id;
	
	@OneToOne
	@JoinColumn(name= "writer") // 실제 DB의 컬럼 이름
	private User writer;
	@OneToOne
	@JoinColumn(name= "reported")
	private User reported;
	private String context;
	private String category;
	private int status;
}
