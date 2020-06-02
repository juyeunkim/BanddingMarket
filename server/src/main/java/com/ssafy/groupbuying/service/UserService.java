package com.ssafy.groupbuying.service;

import java.util.List;



import com.ssafy.groupbuying.vo.User;


public interface UserService {

	User findByMail(String uMail);

	void deleteByMail(String uMail);

	List<User> findAll();

	void updateByUser( User user);

	int save(User user);

	Boolean checkByMail(String uMail);

	Boolean checkPass(User user);

}
