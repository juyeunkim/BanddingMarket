package com.ssafy.groupbuying.service;

import java.util.List;



import com.ssafy.groupbuying.vo.User;


public interface UserService {

	User findById(String uId);

	void deleteById(String uId);

	List<User> findAll();

	void updateById(String uId, User user);

	User save(User user);

	Boolean checkById(String uId);

}
