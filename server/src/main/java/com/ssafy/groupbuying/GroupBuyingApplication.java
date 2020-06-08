package com.ssafy.groupbuying;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.groupbuying.interceptor.JwtInterceptor;

import java.util.Arrays;

@SpringBootApplication
public class GroupBuyingApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(GroupBuyingApplication.class, args);
	}

	private static final String[] EXCLUDE_PATHS = {  "/swagger-ui.html/**",
			"/webjars/**",
			"/error/**",
			"/swagger/**",
			"/swagger-resources/**",
			"/null/**",
			"/user/**"

	};

	@Autowired
	private JwtInterceptor jwtInterceptor;

	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor).addPathPatterns("/**")
		//.excludePathPatterns(EXCLUDE_PATHS);
				 .excludePathPatterns(Arrays.asList("/**"));
				//.excludePathPatterns(EXCLUDE_PATHS);
		// 나중에 토큰 체크할 부분a

	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*")
				.exposedHeaders("jwt-auth-token");
	}
}
