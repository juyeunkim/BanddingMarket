package com.ssafy.groupbuying.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.groupbuying.dto.ReportCard;
import com.ssafy.groupbuying.repository.ReportRepository;
import com.ssafy.groupbuying.repository.UserRepository;
import com.ssafy.groupbuying.vo.Report;
import com.ssafy.groupbuying.vo.User;

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
	public void cancleById(long id) {
		// TODO Auto-generated method stub
		Report report = reportRepository.findById(id).get();
		report.setStatus(0);
		reportRepository.save(report);
	}

	@Override
	public void insertReport(Report report) {
		// TODO Auto-generated method stub
		reportRepository.save(report);
	}

	@Override
	public List<ReportCard> findAll() {
		// TODO Auto-generated method stub
		List<Report> reportList =  reportRepository.findAll();
		List<ReportCard> reportCardList = new ArrayList<ReportCard>();
		
		for(int i=0; i<reportList.size(); i++) {
			Report report = reportList.get(i);
			User writerUser = report.getWriter();
			User reportedUser = report.getReported();
			
			ReportCard reportCard = new ReportCard();
			reportCard.setReportId(report.getReportId());
			reportCard.setCategory(report.getCategory());
			reportCard.setContext(report.getContext());
			reportCard.setStatus(report.getStatus());
			reportCard.setWriteDate(report.getWriteDate());
			
			// 자식
			reportCard.setWriterEmail(writerUser.getEmail());
			reportCard.setReportedEmail(reportedUser.getEmail());
			
			reportCardList.add(reportCard);
			
		}
		
		return reportCardList;
	}

	@Override
	public void approveById(long id) {
		// TODO Auto-generated method stub
		Report report = reportRepository.findById(id).get();
		report.setStatus(1);
		reportRepository.save(report);
	}

	@Override
	public List<Report> findByYear() {
		return reportRepository.findByYear();
	}

	@Override
	public List<Report> findByMonth() {
		// TODO Auto-generated method stub
		return reportRepository.findByMonth();
	}

	@Override
	public List<Report> findByWeek() {
		// TODO Auto-generated method stub
		return reportRepository.findByWeek();
	}

	@Override
	public List<Report> findByCategory(String category) {
		// TODO Auto-generated method stub
		System.out.println("service: "+category);

		return reportRepository.findByCategory(category);
	}

}
