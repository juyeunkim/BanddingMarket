package com.ssafy.groupbuying.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.groupbuying.service.ReportService;
import com.ssafy.groupbuying.vo.Report;
import com.ssafy.groupbuying.vo.User;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("report")
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

	@PostMapping()
	@ApiOperation(value = "모든 신고 ", notes = "")
	public ResponseEntity<List<Report>> reportAll() {

		return new ResponseEntity<List<Report>>(reportService.findAll(), HttpStatus.OK);

	}

	@PostMapping(value = "/searchReport")
	@ApiOperation(value = "특정유저 신고 목록 ", notes = "")
	public ResponseEntity<List<Report>> reportByWriter(@RequestParam String writer) {

		return new ResponseEntity<List<Report>>(reportService.findByWriter(writer), HttpStatus.OK);

	}
}
