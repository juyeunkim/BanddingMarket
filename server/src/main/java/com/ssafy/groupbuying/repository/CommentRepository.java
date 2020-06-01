package com.ssafy.groupbuying.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.groupbuying.vo.Board;
import com.ssafy.groupbuying.vo.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	public Comment findById(long id);
	public List<Comment> findByBoard(Board board);
}
