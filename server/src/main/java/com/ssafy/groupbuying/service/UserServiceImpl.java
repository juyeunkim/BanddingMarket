package com.ssafy.groupbuying.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ssafy.groupbuying.dto.UserAndBoardCount;
import com.ssafy.groupbuying.repository.BoardRepository;
import com.ssafy.groupbuying.repository.UserRepository;
import com.ssafy.groupbuying.vo.Board;
import com.ssafy.groupbuying.vo.User;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BoardRepository boardRepository;
	@Override
	public User findByMail(String uMail) {
		// TODO Auto-generated method stub
		
		
		return userRepository.findByMail(uMail);
	}
	
	@Override
	public Boolean checkByMail(String uMail) {
		// TODO Auto-generated method stub
		
		
		return new Boolean((userRepository.existsByMail(uMail) != 0));
	}

	@Override
	public void deleteByMail(String uMail) {
		// TODO Auto-generated method stub
		userRepository.deleteByMail(uMail);
	}

	@Override
	public List<User> findAll() {
		// juheekim null - > findAll로 수정 
		return userRepository.findAll();
	}

	@Override
	public void updateByUser(User user) {
		// TODO Auto-generated method stub
		userRepository.save(user);
	}

	@Override
	public int save(User user) {
		// TODO Auto-generated method stub
		System.out.println(user.getEmail()+ user.getPassword()+ user.getNickname()+ user.getName());
		return userRepository.signUp(user.getEmail(), user.getPassword(), user.getNickname(), user.getName());
	}

	@Override
	public Boolean checkPass(User user) {
		// TODO Auto-generated method stub
		return new Boolean(userRepository.checkPass(user.getEmail(), user.getPassword()) != 0) ;
	}

	@Override
	public List<Board> findBoardByUser(User user) {
		// TODO Auto-generated method stub
		return boardRepository.findByUser(user);
	}

	@Override
	public List<UserAndBoardCount> findTopUser() {
		// TODO Auto-generated method stub
				List<User> list = userRepository.findTopUser();;
				List<UserAndBoardCount> uList = new ArrayList<UserAndBoardCount>() ;
				
				
				
				
				for(int i=0; i<list.size(); i++) {
					//user count repository 
					//userRepository.userBoardCount(list.get(i).getUser_id());
					System.out.println(userRepository.userBoardCount(list.get(i).getUser_id()));
					User user = list.get(i);
					UserAndBoardCount userCount = new UserAndBoardCount();
					userCount.setEmail(user.getEmail());
					userCount.setName(user.getName());
					userCount.setNickname(user.getNickname());
					userCount.setReputation(user.getReputation());
					userCount.setUser_id(user.getUser_id());
					userCount.setBoardCnt(userRepository.userBoardCount(user.getUser_id()));
					
					
					uList.add(i, userCount);
					
					//uList 담기
				}
				
				return uList;
	}

	@Override
	public List<User> findAlluser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}
	
	
}
