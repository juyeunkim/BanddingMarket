package com.ssafy.groupbuying.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.groupbuying.vo.Board;

public interface CommentBoardRepository extends JpaRepository<Board, Long> {

}
