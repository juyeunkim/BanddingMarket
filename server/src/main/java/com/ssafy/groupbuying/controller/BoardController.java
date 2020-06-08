package com.ssafy.groupbuying.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	@ApiOperation(value = " 게시판 ID | 게시판 조회 | Board ")
	public Object getBoard(@PathVariable(required = true) final long id) {
		final BasicResponse result = new BasicResponse();
    	result.status = true;
		result.data = "게시판 조회"; 
		result.object = service.getBoard(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/board")
	@ApiOperation(value = "유효한 게시판 조회(삭제된거 제외)  | Board List")
	public Object getBoards() {
		final BasicResponse result = new BasicResponse();
    	result.status = true;
		result.data = "전체 게시판 조회"; 
		result.object = service.getBoards();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping("/board")
	@ApiOperation(value = " | 게시판 등록 | Board", notes = "필요한 컬럼 : category, context, limit_num, deadline_date, title, user:{user_id} , (생략 가능 - board_locationX, board_locationY)")
	public Object insert(@RequestBody(required = true) Board board) {
		final BasicResponse result = new BasicResponse();
    	result.status = service.insert(board);
		result.data = "게시판 등록"; 
		result.object = board;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping("/board/searchById")
	@ApiOperation(value = " user_id | 유저가 작성한 게시판 | Board List", notes = "필요한 컬럼 : user:{user_id}")
	public Object searchById(@RequestBody(required = true) final long user_id) {
		final BasicResponse result = new BasicResponse();
    	result.status = true;
		result.data = user_id+"유저가 작성한 게시판"; 
		result.object = service.search(user_id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/board/searchByYear")
	@ApiOperation(value = " | 년도별 게시판 | Board List")
	public Object searchByYear() {
		final BasicResponse result = new BasicResponse();
    	result.status = true;
		result.data = "년도별 게시판"; 
		result.object = service.searchByYear();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/board/searchByMonth")
	@ApiOperation(value = " | 월별 게시판 | Board List")
	public Object searchByMonth() {
		final BasicResponse result = new BasicResponse();
    	result.status = true;
		result.data = "월별 게시판"; 
		result.object = service.searchByMonth();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/board/searchByWeek")
	@ApiOperation(value = " | 주간별 게시판 | Board List")
	public Object searchByWeek() {
		final BasicResponse result = new BasicResponse();
    	result.status = true;
		result.data = "주간별 게시판"; 
		result.object = service.searchByWeek();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PutMapping("/board")
	@ApiOperation(value = "게시판 수정 | 수정된 Board", notes = "필요한 컬럼 : board_id, category, context, limit_num, deadline_date, title, user:{user_id} | status가 false이면 제한인원 초과로 수정 불가")
	public Object update(@RequestBody(required = true) Board b) {
		final BasicResponse result = new BasicResponse();
    	result.status = service.insert(b);
		result.data = "게시판 수정"; 
		result.object = b;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/board/{id}")
	@ApiOperation(value = "params = board_id | 게시판 삭제 | 삭제된 Board")
	public Object delete(@PathVariable(required = true) final long id) {
		final BasicResponse result = new BasicResponse();
    	Board board= service.getBoard(id);
		result.status = service.delete(id);
		result.data = "게시판 삭제"; 
		result.object = board;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/board/{bid}/{uid}")
	@ApiOperation(value = "params = board_id, user_id  | 참가 신청 | 0 - 참가성공, 1 - 마감, 2 - 중복된유저 신청, 3 - 제한인원 초과 ")
	public Object apply(@PathVariable(required = true) final long bid,
			@PathVariable(required = true) final long uid) {
		
		final BasicResponse result = new BasicResponse();
		int val = service.apply(bid, uid);

		if(val == 0) result.status = true;
		else result.status = false;
		result.data = "참가 신청"; 
		result.object = val; 
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/board/{bid}/{uid}")
	@ApiOperation(value = "params = board_id, user_id  | 참가 취소 | 0 - 취소 성공, 1 - 마감, 2 - 없는 유저 취소, 3 - 호스트가 취소")
	public Object cancel(@PathVariable(required = true) final long bid,
			@PathVariable(required = true) final long uid) {
		
		final BasicResponse result = new BasicResponse();
		int val = service.cancel(bid, uid);
		
		if(val == 0) result.status = true;
		else result.status = false;
		result.data = "참가 취소"; 
		result.object = val;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/board/comment/{bid}")
	@ApiOperation(value = " board_id | 댓글 조회 | Board에 달린 댓글들 조회")
	public Object getComment(@PathVariable(required = true) final long bid) {
		final BasicResponse result = new BasicResponse();
    	result.status = true;
		result.data = "댓글 조회"; 
		result.object = service.getComments(bid);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping("/board/comment")
	@ApiOperation(value = "댓글 등록 | 등록한 Comment", notes = "필요한 컬럼 : board_id, context, user:{user_id}")
	public Object insertComment(@RequestBody(required = true) Comment c) {
		final BasicResponse result = new BasicResponse();
    	result.status = service.insertComment(c);
		result.data = "댓글 등록"; 
		result.object = c;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PutMapping("/board/comment")
	@ApiOperation(value = "댓글 수정 | 수정된 Comment", notes = "필요한 컬럼 : comment_id, board_id, context, user:{user_id}")
	public Object updateComment(@RequestBody(required = true) Comment c) {
		final BasicResponse result = new BasicResponse();
    	result.status = service.updateComment(c);
		result.data = "댓글 수정"; 
		result.object = c;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/board/comment/{cid}")
	@ApiOperation(value = "comment_id | 댓글 삭제 | 삭제된 Comment")
	public Object deleteComment(@PathVariable(required = true) final long cid) {
		final BasicResponse result = new BasicResponse();
    	result.status = true;
		result.data = "댓글 삭제"; 
		result.object = service.deleteComment(cid);
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
	
//	@GetMapping("/board/category/{category}") 
//	@ApiOperation(value = "검색하려는 카테고리를 입력 ex)11(한식 의미) | 해당 카테고리에 속한 board 리스트를 리턴해줌 | board 객체")
//	public Object getCategoryBoard(@RequestParam(required = true) String category) {
//		final BasicResponse result = new BasicResponse();
//    	result.status = true;
//		result.data = "카테고리별 보기"; 
//		result.object = service.getCategoryBoard(category);
//		return new ResponseEntity<>(result, HttpStatus.OK);
//	}
	
	@PostMapping("/board/keyword") 
	@ApiOperation(value = "검색하려는 키워드를 #로 구분해 입력(최대 3개) #떡볶이#마라탕#치킨 | 하나라도 키워드가 포함되어있으면 그 board 정보를 리턴해줌 | board 객체")
	public Object getKeywordSearch(@RequestBody(required = true) String keyword) {
		final BasicResponse result = new BasicResponse();
    	result.status = true;
		result.data = "키워드별 보기"; 
		result.object = service.getKeywordSearch(keyword);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
}
