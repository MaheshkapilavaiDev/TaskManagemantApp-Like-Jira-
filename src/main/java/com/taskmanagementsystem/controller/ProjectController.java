package com.taskmanagementsystem.controller;

import org.springframework.web.bind.annotation.*;

import com.taskmanagementsystem.dto.ProjectDTO;
import com.taskmanagementsystem.entity.Project;
import com.taskmanagementsystem.service.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

	private final ProjectService service;

	public ProjectController(ProjectService service) {
		this.service = service;
	}

	@PostMapping
	public Project create(@RequestBody ProjectDTO dto) {
		return service.create(dto);
	}

	@GetMapping
	public List<ProjectDTO> getAll() {
		return service.getAll();
	}

	@GetMapping("/{id}")
	public ProjectDTO getById(@PathVariable Long id) {
		return service.getById(id);
	}

	@PutMapping("/{id}")
	public ProjectDTO update(@PathVariable Long id, @RequestBody ProjectDTO dto) {

		return service.update(id, dto);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
}
