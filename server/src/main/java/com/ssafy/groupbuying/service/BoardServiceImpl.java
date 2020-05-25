package com.ssafy.groupbuying.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.groupbuying.repository.BoardRepository;
import com.ssafy.groupbuying.vo.Board;
import com.ssafy.groupbuying.vo.User;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardRepository repo;
	
	@Override
	public boolean insert(Board board) {
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!board");
		repo.save(board);
		return true;
	}

	@Override
	public boolean update(Board board) {
		repo.save(board);
		return true;
	}

	@Override
	public boolean delete(int id) {
		Board board = repo.findById(id);
		repo.delete(board);
		return true;
	}

	@Override
	public Board getBoard(int id) {
		return repo.findById(id);
	}

	@Override
	public List<Board> getBoards() {
		return repo.findAll();
	}


}
