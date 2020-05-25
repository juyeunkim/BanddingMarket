package com.ssafy.groupbuying.service;

import java.util.List;



import com.ssafy.groupbuying.vo.User;


public interface UserService {

	User findByMail(String uMail);

	void deleteByMail(String uMail);

	List<User> findAll();

	void updateById(String uId, User user);

	User save(User user);

	Boolean checkByMail(String uMail);

	Boolean checkPass(User user);

}
