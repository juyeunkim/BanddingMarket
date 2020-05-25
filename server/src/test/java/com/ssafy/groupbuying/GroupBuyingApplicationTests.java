package com.ssafy.groupbuying;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.groupbuying.repository.BoardRepository;
import com.ssafy.groupbuying.repository.UserRepository;
import com.ssafy.groupbuying.vo.Board;
import com.ssafy.groupbuying.vo.User;

@SpringBootTest
class GroupBuyingApplicationTests {

	@Autowired
    BoardRepository BoardRepository;
	@Autowired
    UserRepository UserRepository;
	
	
	@Test
	void insertUser() {
		
	}
	
	@Test
	void insertBoard() {
		
	}

}
