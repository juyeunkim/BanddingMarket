package com.ssafy.groupbuying.interceptor;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.groupbuying.service.JwtService;
import com.ssafy.groupbuying.vo.User;


@Component

public class JwtInterceptor implements HandlerInterceptor{
	
	@Autowired
	private JwtService jwtService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println(request.getMethod()+":" + request.getServletPath());
			if(request.getMethod().equals("OPTIONS")) {
				return true;
			} else {
				
				String token = request.getHeader("jwt-auth-token");
				if(token != null && token.length() > 0) {
					jwtService.checkValid(token);
					String path=request.getServletPath();
					
					
					if(path.contains("/board"))  {
						
						Map<String, String> map = (LinkedHashMap<String, String>)jwtService.get(token).get("User");
						
						String role = map.get("role");
						if(role.equals("bad")){
							throw new RuntimeException("다수 신고 누적으로 일시정지된 유저입니다 ");
						}
						
					}
				
					
					return true;
				} else {
					throw new RuntimeException("인증토큰이 없습니다. ");
				}
			}
	
	}

}
