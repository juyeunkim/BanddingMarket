package com.ssafy.groupbuying.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.groupbuying.vo.Board;

public interface SafeLocationRepository extends JpaRepository<Board, Integer> {

}
