package com.ssafy.groupbuying.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.groupbuying.repository.BoardRepository;
import com.ssafy.groupbuying.repository.ParticipantsRepository;
import com.ssafy.groupbuying.repository.UserRepository;
import com.ssafy.groupbuying.vo.Board;
import com.ssafy.groupbuying.vo.Participants;
import com.ssafy.groupbuying.vo.User;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardRepository repo;
	
	@Autowired
	ParticipantsRepository prepo;
	
	@Autowired
	UserRepository urepo;
	
	@Override
	public boolean insert(Board board) {
		// 게시판을 작성하고
		repo.save(board);
		return true;
	}

	@Override
	public boolean update(Board board) {
		repo.save(board);
		return true;
	}

	@Override
	public boolean delete(long id) {
		// is_deleted 값에 true
		Board board = repo.findById(id);
		board.setDeleted(true);
		repo.save(board);
		return true;
	}

	@Override
	public Board getBoard(long id) {
		return repo.findById(id);
	}

	@Override
	public List<Board> getBoards() {
		return repo.findAll();
	}

	@Override
	public boolean apply(long bid, long uid) {
		// 기존에 있는 참가자수에 +1 후, UPDATE
		Board board = getBoard(bid);
		int attend = board.getParticipants();
		board.setParticipants(attend+1);
		update(board);
//	
		User user = urepo.findById(uid);
		Participants newUser =new Participants();
		newUser.setUser(user);
		newUser.setBoard(board);
		
		prepo.save(newUser);
		
		return true;
	}

	


}
