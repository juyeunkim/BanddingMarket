package com.ssafy.groupbuying.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.groupbuying.dto.DealInfo;
import com.ssafy.groupbuying.model.BasicResponse;
import com.ssafy.groupbuying.service.MapService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("map")
public class MapController {

	@Autowired
	private MapService service;

	@GetMapping("/searchAll")
	@ApiOperation("전체 리스트 조회")
	public Object getBoards() {
		final BasicResponse result = new BasicResponse();
    	result.status = true;
		result.data = "전체 리스트 조회"; 
		result.object = service.search();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping("/search/boardInDist")
	@ApiOperation("사용자 거리 기반 게시글 검색")
	public Object getBoards(double latitude, double longitude, int dist) {
		System.out.println(">>>>>>>>>");
		System.out.println(latitude);
		System.out.println(">>>>>>>>>");
		final BasicResponse result = new BasicResponse();
    	result.status = true;
		result.data = "사용자 거리 기반 게시글 검색"; 
		result.object = service.getBoard(latitude, longitude, dist);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	// Exception Handle
	public ResponseEntity<Map<String, Object>> handleFail(Object data, HttpStatus state) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("state", "fail");
		resultMap.put("data", data);
		return new ResponseEntity<Map<String, Object>>(resultMap, state);
	}

	public ResponseEntity<Map<String, Object>> handleSuccess(Object data) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("state", "ok");
		resultMap.put("data", data);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
}
