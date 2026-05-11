package com.taskmanagementsystem.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.taskmanagementsystem.dto.TaskRequestDTO;
import com.taskmanagementsystem.entity.Task;
import com.taskmanagementsystem.enums.TaskStatus;
import com.taskmanagementsystem.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	private final TaskService service;

	public TaskController(TaskService service) {
		this.service = service;
	}

	@PostMapping
	public Task create(@RequestBody TaskRequestDTO dto) {
		return service.create(dto);
	}

	@GetMapping
	public List<Task> getAll() {
		return service.getAll();
	}

	@GetMapping("/{id}")
	public Task getById(@PathVariable Long id) {
		return service.getById(id);
	}

	@PutMapping("/{id}")
	public Task update(@PathVariable Long id, @RequestBody TaskRequestDTO dto) {

		return service.update(id, dto);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

	@GetMapping("/project/{projectId}")
	public List<Task> getByProject(@PathVariable Long projectId) {

		return service.getTasksByProject(projectId);
	}

	@GetMapping("/user/{userId}")
	public List<Task> getByUser(@PathVariable Long userId) {

		return service.getTasksByUser(userId);
	}

	@GetMapping("/status/{status}")
	public List<Task> getByStatus(@PathVariable TaskStatus status) {

		return service.getTasksByStatus(status);
	}

	@GetMapping("/search")
	public Page<Task> search(@RequestParam String keyword, Pageable pageable) {

		return service.search(keyword, pageable);
	}

	@GetMapping("/pagination")
	public Page<Task> pagination(Pageable pageable) {

		return service.getAllWithPagination(pageable);
	}
}
