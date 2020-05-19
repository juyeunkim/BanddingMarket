package com.ssafy.groupbuying.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.groupbuying.vo.User;

public interface UserRepository extends JpaRepository<User, String> {

}
