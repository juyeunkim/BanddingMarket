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
//	@Query(value = "select * from board", nativeQuery = true)
	@Query(value = "SELECT b.board_id, b.user_id, b.title, b.context, b.board_locationX, b.board_locationY, b.write_date, b.deadline_date, b.limit_num, b.participants, b.category, b.is_Deleted FROM board b"
			+ " JOIN (SELECT board_id, ( 6371 * acos( cos( radians( :latitude) ) * cos( radians( board_locationy ) )"
			+ " * cos( radians( board_locationx ) - radians(:longitude) )"
			+ " + sin( radians(:latitude) ) * sin( radians( board_locationy ) ) ) ) AS distance"
			+ " FROM board WHERE category = :category ORDER BY distance LIMIT 0 , 20) d USING (board_id)"
			+ " WHERE d.distance < (:dist / 1000)"
			, nativeQuery = true)
	List<Board> getBoard(@Param("latitude") double latitude, @Param("longitude") double longitude, @Param("dist") int dist, @Param("category") int category);
	
	
}
