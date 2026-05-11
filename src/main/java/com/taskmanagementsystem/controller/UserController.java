package com.taskmanagementsystem.controller;

import org.springframework.web.bind.annotation.*;

import com.taskmanagementsystem.dto.UserRequestDTO;
import com.taskmanagementsystem.entity.User;
import com.taskmanagementsystem.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

	@PostMapping("/add")
	public User create(@RequestBody UserRequestDTO dto) {
		return service.create(dto);
	}

	@GetMapping("/getAllUsers")
	public List<User> getAll() {
		return service.getAll();
	}

	@GetMapping("/{id}")
	public UserRequestDTO getById(@PathVariable Long id) {
		return service.getById(id);
	}

	@PutMapping("/{id}")
	public UserRequestDTO update(@PathVariable Long id, @RequestBody UserRequestDTO dto) {

		return service.update(id, dto);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
}
