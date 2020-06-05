package com.ssafy.groupbuying.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.groupbuying.vo.Board;
import com.ssafy.groupbuying.vo.User;

public interface BoardRepository extends JpaRepository<Board, Long> {
	public Board findById(long id);

	@Query(value = "SELECT b.board_id, b.user_id, b.title, b.context, b.board_locationX, b.board_locationY, b.write_date, b.deadline_date, b.limit_num, b.participants, b.is_Deleted, b.category, b.keyword FROM board b"
			+ " JOIN (SELECT board_id, ( 6371 * acos( cos( radians( :latitude) ) * cos( radians( board_locationy ) )"
			+ " * cos( radians( board_locationx ) - radians(:longitude) )"
			+ " + sin( radians(:latitude) ) * sin( radians( board_locationy ) ) ) ) AS distance"
			+ " FROM board WHERE category = :category ORDER BY distance LIMIT 0 , 20) d USING (board_id)"
			+ " WHERE d.distance < (:dist / 1000)"
			+ " and b.is_deleted != 1 and now() < b.deadline_date and b.participants < b.limit_num"
			, nativeQuery = true)
	public List<Board> getBoardInDist(@Param("latitude") double latitude, @Param("longitude") double longitude, @Param("dist") int dist, @Param("category") int category);

	
//	public List<Board> findByType(int type);
//	public List<Board> findByCategoryLike(String categoryList);

//	@Query(value = "SELECT * FROM board b WHERE category like %:category1% or category like %:category2% or category like %:category3%", nativeQuery = true)
//	public List<Board> findByCategoryLike(@Param("category1") String category1, @Param("category2") String category2, @Param("category3") String category3);

	@Query(value = "SELECT * FROM board b WHERE keyword like %:keyword1% or keyword like %:keyword2% or keyword like %:keyword3%", nativeQuery = true)
	public List<Board> findByKeywordLike(String keyword1, String keyword2, String keyword3);
	
	public List<Board> findByUser(User user);
	
	@Query(value = "select * from bandding.board where year(now()) = year(write_date) ", nativeQuery = true)
	public List<Board> findByYear();
	@Query(value = "select * from bandding.board where month(now()) = month(write_date) ", nativeQuery = true)
	public List<Board> findByMonth();
	@Query(value = "select * from bandding.board where day(write_date) between day(now())-7 and day(now()) ", nativeQuery = true)
	public List<Board> findByWeek();
}
