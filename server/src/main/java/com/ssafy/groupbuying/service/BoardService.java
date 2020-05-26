package com.ssafy.groupbuying.service;

import java.util.List;

import com.ssafy.groupbuying.vo.Board;
import com.ssafy.groupbuying.vo.Comment;
import com.ssafy.groupbuying.vo.Participants;

public interface BoardService {
	// 게시판 CRUD
	public boolean insert(Board board);
	public boolean update(Board board);
	public boolean delete(long id);
	public Board getBoard(long id);
	public List<Board> getBoards();
	
	// 신청
	public Participants apply(long bid, long uid);
	// 취소
	public Participants cancel(long bid, long uid);
	
	// 댓글 CRUD
	public boolean insertComment(Comment com);
	public Comment deleteComment(long cid);
	public boolean updateComment(Comment com);
	public List<Comment> getComments(long bid);
}
