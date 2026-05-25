package com.ecommerce.user_servise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.user_servise.model.User;
import com.ecommerce.user_servise.repository.UserRepository;
import com.ecommerce.user_servise.security.JwtUtil;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	public String register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);
        return "User registered successfully!";
    }
	
	
	public String login(String username, String rawPassword) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Invalid password!");
        }

        return jwtUtil.generateToken(username);
    }

}
