package com.ssafy.groupbuying.service;

import java.util.List;

import com.ssafy.groupbuying.vo.Report;

public interface ReportService {

	
	List<Report> findAll();
	List<Report> findByWriter(long writer);
	void denyById(long id);
	void approveById(long id);
	void insertReport(Report report);
	
}
