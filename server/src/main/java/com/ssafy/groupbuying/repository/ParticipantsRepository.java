package com.ssafy.groupbuying.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.groupbuying.vo.Board;
import com.ssafy.groupbuying.vo.Participants;

public interface ParticipantsRepository extends JpaRepository<Participants, Long> {

}
