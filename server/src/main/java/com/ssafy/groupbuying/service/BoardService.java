package com.ssafy.groupbuying.service;

import java.util.List;

import com.ssafy.groupbuying.vo.Board;

public interface BoardService {
	// CRUD
	public boolean insert(Board board);
	public boolean update(Board board);
	public boolean delete(int id);
	public Board getBoard(int id);
	public List<Board> getBoards();
}
