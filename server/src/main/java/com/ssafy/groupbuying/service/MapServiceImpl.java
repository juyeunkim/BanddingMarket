package com.ssafy.groupbuying.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.groupbuying.repository.SafeLocationRepository;
import com.ssafy.groupbuying.vo.SafeLocation;

@Service
public class MapServiceImpl implements MapService {

	@Autowired
	SafeLocationRepository repo;
	
	@Override
	public List<SafeLocation> search() {
		return repo.findAll();
	}
}
