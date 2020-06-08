package com.ssafy.groupbuying.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	@ApiOperation("파라미터 없음 | 전체 안전 거래 장소 리스트 조회(지하철, CCTV) | SafeLocation List 반환")
	public Object getAllSafeLocations() {
		final BasicResponse result = new BasicResponse();
    	result.status = true;
		result.data = "전체 리스트 조회"; 
		result.object = service.search();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping("/search/safeLocation")
	@ApiOperation("위도, 경도, 거리(m단위) | 사용자 위치 기반 00m 이내의 안전 거래 장소 리스트 조회 | SafeLocation List 반환")
	public Object getSafeLocations(@RequestBody(required = true) DealInfo info) {
//		public Object getSafeLocations(@RequestBody(required = true) double latitude, @RequestBody(required = true) double longitude, @RequestBody(required = true) int dist) {
		final BasicResponse result = new BasicResponse();
    	result.status = true;
		result.data = "사용자 위치 기반 게시글 검색"; 
		result.object = service.getSafeLocations(info.getLatitude(), info.getLongitude(), info.getDist());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping("/search/boardInDist")
	@ApiOperation("위도, 경도, 거리(m단위), 카테고리(0:택배/1:음식) 순으로 입력 | 사용자 위치 기반 게시글 검색 (현재 거래 가능한 게시물만) | Board List 반환")
	public Object getBoards(@RequestBody(required = true) DealInfo info) {
//		public Object getBoards(@RequestBody(required = true) double latitude, @RequestBody(required = true) double longitude, @RequestBody(required = true) int dist, @RequestBody(required = true) int category) {
		final BasicResponse result = new BasicResponse();
    	result.status = true;
		result.data = "사용자 위치 기반 게시글 검색";
		result.object = service.boardInDist(info.getLatitude(), info.getLongitude(), info.getDist(), info.getCategory());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping("/search/recoBoardsByDeadline")
	@ApiOperation("위도, 경도, 거리(m단위), 카테고리(0:택배/1:음식) 순으로 입력 | 사용자 위치 기반으로 주변의 마감이 얼마 남지 않은 게시글 추천 | Board List 반환")
	public Object recoBoardsByDeadline(@RequestBody(required = true) DealInfo info) {
		final BasicResponse result = new BasicResponse();
    	result.status = true;
		result.data = "사용자 위치 기반 마감 예정 게시글 추천";
		result.object = service.recoBoardsByDeadline(info.getLatitude(), info.getLongitude(), info.getDist(), info.getCategory());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping("/search/recoBoardsByParticipants")
	@ApiOperation("위도, 경도, 거리(m단위), 카테고리(0:택배/1:음식) 순으로 입력 | 사용자 위치 기반으로 참가자가 많은 게시글 추천 | Board List 반환")
	public Object recoBoardsByParticipants(@RequestBody(required = true) DealInfo info) {
		final BasicResponse result = new BasicResponse();
    	result.status = true;
		result.data = "사용자 위치 기반 참가자 많은 게시글 추천";
		result.object = service.recoBoardsByParticipants(info.getLatitude(), info.getLongitude(), info.getDist(), info.getCategory());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
