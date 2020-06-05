package com.ssafy.groupbuying.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.groupbuying.vo.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {

	public List<Report> findByWriter(String writer);
	
	
}
