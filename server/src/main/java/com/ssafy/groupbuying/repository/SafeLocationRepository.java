package com.ssafy.groupbuying.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.groupbuying.vo.SafeLocation;

@Repository
public interface SafeLocationRepository extends JpaRepository<SafeLocation, Long> {

	@Query(value = "SELECT * FROM safe_location "
			+ " WHERE id in"
			+ " (SELECT min(id) FROM safe_location"
			+ " WHERE round( 6371 * acos( cos( radians(:latitude) ) * cos( radians( latitude ) )"
			+ " * cos( radians( longitude ) - radians(:longitude) )"
			+ " + sin( radians(:latitude) ) * sin( radians( latitude ) ) ) * 1000) < :dist"
			+ " and (latitude != :latitude and longitude != :longitude)"
			+ " group by name)"
			, nativeQuery = true)
	List<SafeLocation> getSafeLocations(@Param("latitude") double latitude, @Param("longitude") double longitude, @Param("dist") int dist);
}
