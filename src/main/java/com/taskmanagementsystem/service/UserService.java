package com.taskmanagementsystem.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.taskmanagementsystem.dto.UserRequestDTO;
import com.taskmanagementsystem.entity.User;
import com.taskmanagementsystem.enums.Role;
import com.taskmanagementsystem.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository repository;
	private final PasswordEncoder encoder;

	public UserService(UserRepository repository, PasswordEncoder encoder) {
		this.repository = repository;
		this.encoder = encoder;
	}

	public User create(UserRequestDTO dto) {

		User user = new User();

		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setPassword(encoder.encode(dto.getPassword()));
		user.setRole(Role.USER);

		return repository.save(user);
	}

	public List<User> getAll() {
		return repository.findAll();
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	public UserRequestDTO update(Long id, UserRequestDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserRequestDTO getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}