package com.ssafy.groupbuying.service;

import java.util.List;

import javax.persistence.Convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
		// TODO Auto-generated method stub
		return null;
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
	public List<User> findTopUser() {
		// TODO Auto-generated method stub
		return userRepository.findTopUser();
	}

	@Override
	public List<User> findAlluser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}
	
	
}
