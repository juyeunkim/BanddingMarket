package com.ssafy.groupbuying.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.groupbuying.service.TestService;

@RestController
@RequestMapping("map")
public class MapController {

	@Autowired
	private TestService service;


}
