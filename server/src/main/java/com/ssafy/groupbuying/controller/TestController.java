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
@RequestMapping("test")
public class TestController {


		@Autowired
		private TestService service;
		//의안 검색(,)  //의원이 발의한 의안  // 의안상세//
		
}
