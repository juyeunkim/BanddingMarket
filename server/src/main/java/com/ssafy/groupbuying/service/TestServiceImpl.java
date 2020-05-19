package com.ssafy.groupbuying.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.groupbuying.repository.TestRepository;
import com.ssafy.groupbuying.vo.Test;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	TestRepository repository;
	@Override
	public List<Test> testSelect() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

}
