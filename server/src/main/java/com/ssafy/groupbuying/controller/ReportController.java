package com.ssafy.groupbuying.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.groupbuying.dto.ReportCard;
import com.ssafy.groupbuying.service.ReportService;
import com.ssafy.groupbuying.vo.Report;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("report")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class ReportController {

	@Autowired
	private ReportService reportService;

	// 유저 Mail로 아이디 삭제

	@PutMapping(value = "/deny")
	@ApiOperation(value = "신고 거절", notes = "param=report_id ")
	public ResponseEntity<Void> denyReport(@RequestBody Report report) {
		reportService.denyById(report.getReportId());

		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@PutMapping(value = "/approve")
	@ApiOperation(value = "신고 승인", notes = "param=report_id ")
	public ResponseEntity<Void> approveReport(@RequestBody Report report) {
		reportService.approveById(report.getReportId());

		return new ResponseEntity<Void>(HttpStatus.OK);

	}
	
	@PutMapping(value = "/cancle")
	@ApiOperation(value = "승인된 신고를 취소", notes = "param=report_id ")
	public ResponseEntity<Void> cancleReport(@RequestBody Report report) {
		reportService.cancleById(report.getReportId());

		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@PostMapping(value = "/insert")
	@ApiOperation(value = "신고하기", notes = "")
	@ApiResponses({ @ApiResponse(code = 200, message = "신고 성공"), @ApiResponse(code = 409, message = "신고 실패") })
	public ResponseEntity<Void> insertReport(@RequestBody Report report) {

		HttpStatus status = HttpStatus.OK;
		try {

			reportService.insertReport(report);
		} catch (Exception e) {
			status = HttpStatus.CONFLICT;
		}
		return new ResponseEntity<Void>(status);

	}

	@PostMapping(value = "/report")
	@ApiOperation(value = "모든 신고 ", notes = "")
	public ResponseEntity<List<ReportCard>> reportAll() {

		return new ResponseEntity<List<ReportCard>>(reportService.findAll(), HttpStatus.OK);

	}

	@PostMapping(value = "/searchReport")
	@ApiOperation(value = "특정유저 신고 목록 ", notes = "")
	public ResponseEntity<List<Report>> reportByWriter(@RequestBody long writer) {

		return new ResponseEntity<List<Report>>(reportService.findByWriter(writer), HttpStatus.OK);

	}
	
	@GetMapping(value = "/findByYear")
	@ApiOperation(value = "년도별 검색 ", notes = "")
	public ResponseEntity<List<Report>> findByYear() {

		return new ResponseEntity<List<Report>>(reportService.findByYear(), HttpStatus.OK);

	}
	
	@GetMapping(value = "/findByMonth")
	@ApiOperation(value = "월별 검색 ", notes = "")
	public ResponseEntity<List<Report>> findByMonth() {

		return new ResponseEntity<List<Report>>(reportService.findByMonth(), HttpStatus.OK);

	}
	
	@GetMapping(value = "/findByWeek")
	@ApiOperation(value = "주간 검색 ", notes = "")
	public ResponseEntity<List<Report>> findByWeek() {

		return new ResponseEntity<List<Report>>(reportService.findByWeek(), HttpStatus.OK);

	}
	
	@GetMapping(value = "/findByCategory")
	@ApiOperation(value = "카테고리별 검색 ", notes = "")
	public ResponseEntity<List<Report>> findByCategory(@RequestParam String category) {
		System.out.println("컨트롤러: "+category);
		return new ResponseEntity<List<Report>>(reportService.findByCategory(category), HttpStatus.OK);

	}
	
}
