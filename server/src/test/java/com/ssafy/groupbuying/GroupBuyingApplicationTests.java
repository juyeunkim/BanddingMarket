package com.ssafy.groupbuying;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ssafy.groupbuying.controller.BoardController;
import com.ssafy.groupbuying.repository.BoardRepository;
import com.ssafy.groupbuying.repository.ParticipantsRepository;
import com.ssafy.groupbuying.repository.UserRepository;
import com.ssafy.groupbuying.vo.Participants;
import com.ssafy.groupbuying.vo.User;

@SpringBootTest
class GroupBuyingApplicationTests {

	@Autowired
    BoardRepository BoardRepository;
	@Autowired
    UserRepository UserRepository;
	@Autowired
	ParticipantsRepository par;
	
	@Autowired
	BoardController BoardController;
	
	private MockMvc mockMvc;
	
	
//	@Test
//	void insertUser() {
//		UserRepository.save(new User("w2", "1004", "유저", "닉네임", "이름", 25, "여", "010-1004-6454"));
//		UserRepository.save(new User("ww", "1004", "유저", "닉네임", "이름", 25, "여", "010-5579-6454"));
//	}
//	
	
//	@Before(value = "")
//	public void setUp() throws Exception{
//		mockMvc = MockMvcBuilders.standaloneSetup(BoardController).build();
//	}
	
	
//	@Test
//	void insertBoard() {
//		
//		// board 객체를 만들고
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("category", 0);
//		map.put("context", "내용내뇽");
//		map.put("limit_num", 3);
//		map.put("title", "제목제목");
//		User u = UserRepository.findByEmail("ww");
//		map.put("user", 0);
//		
//		mockMvc.perform(post("/board/insert"));
//		BoardController.insert(board);
//	}
	
	
	

}
