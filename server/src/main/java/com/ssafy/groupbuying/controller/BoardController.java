package com.ssafy.groupbuying.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.groupbuying.model.BasicResponse;
import com.ssafy.groupbuying.service.BoardService;
import com.ssafy.groupbuying.vo.Board;
import com.ssafy.groupbuying.vo.Comment;
import com.ssafy.groupbuying.vo.Reputation;

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
	
	@GetMapping("/board/{id}")
	@ApiOperation(value = "게시판 조회")
	public Object getBoard(@RequestParam(required = true) final int id) {
		final BasicResponse result = new BasicResponse();
    	result.status = true;
		result.data = "게시판 조회"; 
		result.object = service.getBoard(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/board")
	@ApiOperation(value = "전체 게시판 조회")
	public Object getBoards() {
		final BasicResponse result = new BasicResponse();
    	result.status = true;
		result.data = "전체 게시판 조회"; 
		result.object = service.getBoards();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping("/board")
	@ApiOperation(value = "게시판 등록")
	public Object insert(@RequestBody(required = true) Board board) {
		final BasicResponse result = new BasicResponse();
    	result.status = service.insert(board);
		result.data = "게시판 등록"; 
		result.object = board;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PutMapping("/board")
	@ApiOperation(value = "게시판 수정")
	public Object update(@RequestBody(required = true) Board b) {
		final BasicResponse result = new BasicResponse();
    	result.status = service.insert(b);
		result.data = "게시판 수정"; 
		result.object = b;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/board/{id}")
	@ApiOperation(value = "게시판 삭제")
	public Object delete(@RequestParam(required = true) final int id) {
		final BasicResponse result = new BasicResponse();
    	Board board= service.getBoard(id);
		result.status = service.delete(id);
		result.data = "게시판 삭제"; 
		result.object = board;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/board/{bid}/{uid}")
	@ApiOperation(value = "참가 신청")
	public Object apply(@RequestParam(required = true) final int bid,
				@RequestParam(required = true) final int uid) {
		
		final BasicResponse result = new BasicResponse();
    	result.status = true;
		result.data = "참가 신청"; 
		result.object = service.apply(bid, uid);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/board/{bid}/{uid}")
	@ApiOperation(value = "참가 취소")
	public Object cancel(@RequestParam(required = true) final int bid,
				@RequestParam(required = true) final int uid) {
		
		final BasicResponse result = new BasicResponse();
    	result.status = true;
		result.data = "참가 취소"; 
		result.object = service.cancel(bid, uid);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/board/comment/{id}")
	@ApiOperation(value = "댓글 조회")
	public Object getComment(@RequestParam(required = true) final int bid) {
		final BasicResponse result = new BasicResponse();
    	result.status = true;
		result.data = "댓글 조회"; 
		result.object = service.getComments(bid);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping("/board/comment")
	@ApiOperation(value = "댓글 등록")
	public Object insertComment(@RequestBody(required = true) Comment c) {
		final BasicResponse result = new BasicResponse();
    	result.status = service.insertComment(c);
		result.data = "댓글 등록"; 
		result.object = c;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PutMapping("/board/comment")
	@ApiOperation(value = "댓글 수정")
	public Object updateComment(@RequestBody(required = true) Comment c) {
		final BasicResponse result = new BasicResponse();
    	result.status = service.updateComment(c);
		result.data = "댓글 수정"; 
		result.object = c;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/board/comment/{id}")
	@ApiOperation(value = "댓글 삭제")
	public Object deleteComment(@RequestParam(required = true) final int bid) {
		final BasicResponse result = new BasicResponse();
    	result.status = true;
		result.data = "댓글 삭제"; 
		result.object = service.deleteComment(bid);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	// 평가하기 ----- USER Controller로 이동해야함 
	@PostMapping("/user/rating")
	@ApiOperation(value = "평가하기")
	public Object rate(@RequestBody(required = true) Reputation rep) {
		final BasicResponse result = new BasicResponse();
    	result.status = service.rate(rep);
		result.data = "평가하기"; 
		result.object = rep;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/board/category/{category}") 
	@ApiOperation(value = "검색하려는 카테고리를 입력 ex)11(한식 의미) | 해당 카테고리에 속한 board 리스트를 리턴해줌 | board 객체")
	public Object getCategoryBoard(@RequestParam(required = true) String category) {
		final BasicResponse result = new BasicResponse();
    	result.status = true;
		result.data = "카테고리별 보기"; 
		result.object = service.getCategoryBoard(category);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/board/keyword/{category}") 
	@ApiOperation(value = "검색하려는 키워드를 #로 구분해 입력(최대 3개) #떡볶이#마라탕#치킨 | 하나라도 키워드가 포함되어있으면 그 board 정보를 리턴해줌 | board 객체")
	public Object getKeywordSearch(@RequestParam(required = true) String keyword) {
		final BasicResponse result = new BasicResponse();
    	result.status = true;
		result.data = "카테고리별 보기"; 
		result.object = service.getKeywordSearch(keyword);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
}
