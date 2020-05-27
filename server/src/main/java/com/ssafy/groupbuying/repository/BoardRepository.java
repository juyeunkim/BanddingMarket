package com.ssafy.groupbuying.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.groupbuying.dto.DealInfo;
import com.ssafy.groupbuying.vo.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	public Board findById(long id);

//	@Query(value = "select new com.ssafy.groupbuying.vo.Board(b.board_id, b.user, b.title, b.context, b.board_locationX, b.board_locationY, b.writeDate, b.deadlineDate, b.limit_num, b.participants, b.category, b.isDeleted) from board b", nativeQuery = true)
	@Query(value = "select * from board", nativeQuery = true)
	List<Board> getBoard(@Param("info") DealInfo info);
}
