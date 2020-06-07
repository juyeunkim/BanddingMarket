package com.ssafy.groupbuying.service;

import java.util.List;

import com.ssafy.groupbuying.dto.ReportCard;
import com.ssafy.groupbuying.vo.Report;

public interface ReportService {

	
	List<ReportCard> findAll();
	List<Report> findByWriter(long writer);
	void denyById(long id);
	void approveById(long id);
	void cancleById(long id);
	void insertReport(Report report);
	
	
	List<Report> findByYear();
	List<Report> findByMonth();
	List<Report> findByWeek();
	List<Report> findByCategory(String category);
	
}
