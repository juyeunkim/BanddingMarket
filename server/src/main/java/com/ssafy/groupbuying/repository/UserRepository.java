package com.ssafy.groupbuying.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.groupbuying.vo.User;

public interface UserRepository extends JpaRepository<User, Long> {

	
	@Query(value = "select EXISTS(select * from user u where  u.mail = :uMail)", nativeQuery = true)
	Boolean  existsByMail(@Param("uMail") String uMail);
	
	@Query(value = "select * from user u where  u.mail = :uMail", nativeQuery = true)
	User  findByMail(@Param("uMail") String uMail);
	
	@Query(value = "delete from user u where  u.mail = :uMail", nativeQuery = true)
	User  deleteByMail(@Param("uMail") String uMail);
	//deleted로 수정해야함 
	
	@Query(value = "select EXISTS(select * from user u where  u.mail = :uMail and u.password = :uPass)", nativeQuery = true)
	Boolean  checkPass(@Param("uMail") String uMail, @Param("uPass") String uPass);
	
	
	//@Modifying(clearAutomatically = true)
}
