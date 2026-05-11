package com.taskmanagementsystem.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.taskmanagementsystem.dto.ProjectDTO;
import com.taskmanagementsystem.entity.Project;
import com.taskmanagementsystem.entity.User;
import com.taskmanagementsystem.exception.ResourceNotFoundException;
import com.taskmanagementsystem.repository.ProjectRepository;
import com.taskmanagementsystem.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

	private final UserRepository repo;

	private final ProjectRepository repository;

	public ProjectService(ProjectRepository repository, UserRepository repo) {
		this.repository = repository;
		this.repo = repo;
	}

	public Project create(ProjectDTO dto) {

		Project project = new Project();

		project.setName(dto.getName());

		project.setDescription(dto.getDescription());

		// GET LOGGED-IN USER
		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = repo.findByEmail(email).orElseThrow(() -> new RuntimeException(("User Not Found")));
		project.setCreatedBy(user);

		return repository.save(project);
	}

	public List<ProjectDTO> getAll() {

		return repository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	public ProjectDTO getById(Long id) {

		Project project = repository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));

		return mapToDTO(project);
	}

	public ProjectDTO update(Long id, ProjectDTO dto) {

		Project project = repository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));

		project.setName(dto.getName());
		project.setDescription(dto.getDescription());

		Project updatedProject = repository.save(project);

		return mapToDTO(updatedProject);
	}

	public void delete(Long id) {

		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found"));

		repository.deleteById(id);
	}

	// ENTITY → DTO
	private ProjectDTO mapToDTO(Project project) {

		ProjectDTO dto = new ProjectDTO();

		dto.setId(project.getId());
		dto.setName(project.getName());
		dto.setDescription(project.getDescription());

		return dto;
	}
}