package com.ssafy.groupbuying.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.groupbuying.vo.Board;

public interface ParticipantsBoardRepository extends JpaRepository<Board, Long> {

}
