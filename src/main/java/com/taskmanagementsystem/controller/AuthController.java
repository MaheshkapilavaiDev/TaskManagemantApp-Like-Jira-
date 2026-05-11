package com.taskmanagementsystem.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.taskmanagementsystem.dto.LoginRequestDTO;
import com.taskmanagementsystem.entity.User;
import com.taskmanagementsystem.repository.UserRepository;
import com.taskmanagementsystem.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final UserRepository repository;
	private final PasswordEncoder encoder;
	private final JwtUtil jwtUtil;

	public AuthController(UserRepository repository, PasswordEncoder encoder, JwtUtil jwtUtil) {

		this.repository = repository;
		this.encoder = encoder;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/login")
	public String login(@RequestBody LoginRequestDTO dto) {

		User user = repository.findByEmail(dto.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));

		if (!encoder.matches(dto.getPassword(), user.getPassword())) {

			throw new RuntimeException("Invalid password");
		}

		return jwtUtil.generateToken(user.getEmail(), user.getRole().name());
	}
}
