package com.ssafy.groupbuying.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.groupbuying.vo.Board;
import com.ssafy.groupbuying.vo.Participants;
import com.ssafy.groupbuying.vo.User;

public interface ParticipantsRepository extends JpaRepository<Participants, Long> {
	public Participants findByUserAndBoard(User user, Board board);
	public List<Participants> findByBoard(Board board);
}
