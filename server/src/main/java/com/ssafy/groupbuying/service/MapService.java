package com.ssafy.groupbuying.service;

import java.util.List;

import com.ssafy.groupbuying.dto.DealInfo;
import com.ssafy.groupbuying.vo.Board;
import com.ssafy.groupbuying.vo.SafeLocation;

public interface MapService {

	List<SafeLocation> search();
	List<SafeLocation> getSafeLocations(double latitude, double longitude, int dist);
	List<Board> boardInDist(double latitude, double longitude, int dist, int category);
	List<Board> recoBoardsByDeadline(double latitude, double longitude, int dist, int category);
	List<Board> recoBoardsByParticipants(double latitude, double longitude, int dist, int category);

}
