package com.ssafy.groupbuying.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.groupbuying.vo.SafeLocation;

@Service
public class MapServiceImpl implements MapService {

	@Override
	public List<SafeLocation> search() {
		List<SafeLocation> list = new LinkedList<SafeLocation>();
		return list;
	}

}
