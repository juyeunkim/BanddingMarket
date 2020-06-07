package com.ssafy.groupbuying.dto;

import com.ssafy.groupbuying.vo.Report;

import lombok.Data;
@Data
public class ReportCard extends Report {
	private String writerEmail;
	private String reportedEmail;
	
	
}
