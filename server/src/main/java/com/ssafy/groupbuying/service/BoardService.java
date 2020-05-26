package com.ssafy.groupbuying.service;

import java.util.List;

import com.ssafy.groupbuying.vo.Board;
import com.ssafy.groupbuying.vo.User;

public interface BoardService {
	// CRUD
	public boolean insert(Board board);
	public boolean update(Board board);
	public boolean delete(long id);
	public Board getBoard(long id);
	public List<Board> getBoards();
	
	// 신청
	public boolean apply(long bid, long uid);
	// 취소
//	public boolean cancel(long id);
}
