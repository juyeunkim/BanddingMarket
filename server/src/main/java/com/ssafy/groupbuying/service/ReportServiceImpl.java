package com.ssafy.groupbuying.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.groupbuying.repository.ReportRepository;
import com.ssafy.groupbuying.repository.UserRepository;
import com.ssafy.groupbuying.vo.Report;

@Service
public class ReportServiceImpl  implements ReportService{

	

	@Autowired
	ReportRepository reportRepository;
	@Autowired
	UserRepository userRepository;
	@Override
	public List<Report> findByWriter(long writer) {
		// TODO Auto-generated method stub
		
		return reportRepository.findByWriter(userRepository.findById(writer).getNickname());
	}

	@Override
	public void denyById(long id) {
		// TODO Auto-generated method stub
		Report report = reportRepository.findById(id).get();
		report.setStatus(2);
		reportRepository.save(report);
	}

	@Override
	public void insertReport(Report report) {
		// TODO Auto-generated method stub
		reportRepository.save(report);
	}

	@Override
	public List<Report> findAll() {
		// TODO Auto-generated method stub
		return reportRepository.findAll();
	}

	@Override
	public void approveById(long id) {
		// TODO Auto-generated method stub
		Report report = reportRepository.findById(id).get();
		report.setStatus(1);
		reportRepository.save(report);
	}

}
