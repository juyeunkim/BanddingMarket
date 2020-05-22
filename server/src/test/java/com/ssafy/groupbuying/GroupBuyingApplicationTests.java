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
//		UserRepository.save(new User("w2", "1004", "유저", "닉네임", "이름", 25, "여", "010-1004-6454"));
//		UserRepository.save(new User("ww", "1004", "유저", "닉네임", "이름", 25, "여", "010-5579-6454"));
	}
	
	@Test
	void insertBoard() {
//		User u = new User("ww", "1004", "유저", "닉네임", "이름", 25, "여", "010-5579-6454");
//		BoardRepository.save(new Board(u,"title","context", 5, 0));
	}

}
