package com.ssafy.groupbuying.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.groupbuying.vo.Board;
import com.ssafy.groupbuying.vo.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {

	public List<Report> findByWriter(String writer);
	
	@Query(value = "select * from bandding.report where year(now()) = year(write_date) ", nativeQuery = true)
	public List<Report> findByYear();
	@Query(value = "select * from bandding.report where month(now()) = month(write_date) ", nativeQuery = true)
	public List<Report> findByMonth();
	@Query(value = "SELECT * FROM report \r\n" + 
			"\r\n" + 
			"WHERE date(write_date) BETWEEN subdate(curdate(),date_format(curdate(),'%w')-1) \r\n" + 
			"\r\n" + 
			"AND subdate(curdate(),date_format(curdate(),'%w')-7); ", nativeQuery = true)
	public List<Report> findByWeek();
	
	public List<Report> findByCategory(String category);
}
