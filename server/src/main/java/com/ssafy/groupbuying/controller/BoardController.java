package com.ssafy.groupbuying.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.groupbuying.model.BasicResponse;
import com.ssafy.groupbuying.service.BoardService;
import com.ssafy.groupbuying.vo.Board;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class BoardController {

	@Autowired
	private BoardService service;
	
	@GetMapping("/board/getBoard/{id}")
	@ApiOperation(value = "게시판 조회")
	public Object getBoard(@RequestParam(required = true) final int id) {
		final BasicResponse result = new BasicResponse();
    	result.status = true;
		result.data = "게시판 조회"; 
		result.object = service.getBoard(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/board/getBoards")
	@ApiOperation(value = "전체 게시판 조회")
	public Object getBoards() {
		final BasicResponse result = new BasicResponse();
    	result.status = true;
		result.data = "전체 게시판 조회"; 
		result.object = service.getBoards();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping("/board/insert")
	@ApiOperation(value = "게시판 등록")
	public Object insert(@RequestBody(required = true) Board board) {
		final BasicResponse result = new BasicResponse();
    	result.status = service.insert(board);
		result.data = "게시판 등록"; 
		result.object = board;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping("/board/update")
	@ApiOperation(value = "게시판 수정")
	public Object update(@RequestBody(required = true) Board b) {
		final BasicResponse result = new BasicResponse();
    	result.status = service.insert(b);
		result.data = "게시판 수정"; 
		result.object = b;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/board/delete/{id}")
	@ApiOperation(value = "게시판 삭제")
	public Object delete(@RequestParam(required = true) final int id) {
		final BasicResponse result = new BasicResponse();
    	Board board= service.getBoard(id);
		result.status = service.delete(id);
		result.data = "게시판 삭제"; 
		result.object = board;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/board/apply/{bid}/{uid}")
	@ApiOperation(value = "참가 신청")
	public Object apply(@RequestParam(required = true) final int bid,
				@RequestParam(required = true) final int uid) {
		
		final BasicResponse result = new BasicResponse();
    	result.status = true;
		result.data = "참가 신청"; 
		result.object = service.apply(bid, uid);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/board/cancel/{bid}/{uid}")
	@ApiOperation(value = "참가 취소")
	public Object cancel(@RequestParam(required = true) final int bid,
				@RequestParam(required = true) final int uid) {
		
		final BasicResponse result = new BasicResponse();
    	result.status = true;
		result.data = "참가 취소"; 
		result.object = service.cancel(bid, uid);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
