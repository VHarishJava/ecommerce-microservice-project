package com.ecommerce.user_servise.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.user_servise.model.User;
import com.ecommerce.user_servise.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public String register(@RequestBody User user) {
		return userService.register(user);
	}
	
	
	@PostMapping("/login")
	 public String login(@RequestBody Map<String, String> request) {
	        return userService.login(
	            request.get("username"),
	            request.get("password")
	        );
	    }
}
