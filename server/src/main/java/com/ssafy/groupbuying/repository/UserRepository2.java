/*package com.ssafy.groupbuying.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.groupbuying.vo.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Transactional
	@Modifying
    @Query(value="insert into user (mail,password,nickname,name) values (:mail,:password,:nickname,:name)", nativeQuery = true)
	int signUp (@Param("mail")String mail,@Param("password")String password, @Param("nickname")String nickname, @Param("name")String name);
	
	@Query(value = "select EXISTS(select * from user u where  u.mail = :uMail)", nativeQuery = true)
	int  existsByMail(@Param("uMail") String uMail);
	
	@Query(value = "select * from user u where  u.mail = :uMail", nativeQuery = true)
	User  findByMail(@Param("uMail") String uMail);
	
	@Query(value = "delete from user u where  u.mail = :uMail", nativeQuery = true)
	User  deleteByMail(@Param("uMail") String uMail);
	//deleted로 수정해야함 
	
	@Query(value = "select EXISTS(select * from user u where  u.mail = :uMail and u.password = :uPass)", nativeQuery = true)
	int  checkPass(@Param("uMail") String uMail, @Param("uPass") String uPass);
	
	
	//@Modifying(clearAutomatically = true)
}*/
