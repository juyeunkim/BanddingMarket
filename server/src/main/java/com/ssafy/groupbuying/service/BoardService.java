package com.ssafy.groupbuying.service;

import java.util.List;

import com.ssafy.groupbuying.vo.Board;
import com.ssafy.groupbuying.vo.Comment;
import com.ssafy.groupbuying.vo.Participants;
import com.ssafy.groupbuying.vo.Reputation;

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
	
	// 평가하기 ----- USER Controller로 이동해야함 
	public boolean rate(Reputation rep);
	
	//카테고리별 검색 (최대 3개까지 가능)
	public List<Board> getCategoryBoard(String category);
//	public List<Board> getCategoryBoard(int type, String category);

	//키워드별 검색 (최대 3개까지 가능)
	public List<Board> getKeywordSearch(String keyword);
}
