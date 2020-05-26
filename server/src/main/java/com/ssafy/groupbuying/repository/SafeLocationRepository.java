package com.ssafy.groupbuying.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.groupbuying.dto.DealInfo;
import com.ssafy.groupbuying.vo.Board;
import com.ssafy.groupbuying.vo.SafeLocation;

@Repository
public interface SafeLocationRepository extends JpaRepository<SafeLocation, Integer> {
	
	@Query(value = "select * from board", nativeQuery = true)
	List<Board> getBoard(DealInfo info);
}
