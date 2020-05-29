package com.ssafy.groupbuying.service;

import java.util.List;

import com.ssafy.groupbuying.dto.DealInfo;
import com.ssafy.groupbuying.vo.Board;
import com.ssafy.groupbuying.vo.SafeLocation;

public interface MapService {

	List<SafeLocation> search();
	List<Board> getBoard(double latitude, double longitude, int dist);

}
