package com.taskmanagementsystem.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.taskmanagementsystem.dto.TaskRequestDTO;
import com.taskmanagementsystem.entity.Project;
import com.taskmanagementsystem.entity.Task;
import com.taskmanagementsystem.entity.User;
import com.taskmanagementsystem.enums.TaskStatus;
import com.taskmanagementsystem.exception.ResourceNotFoundException;
import com.taskmanagementsystem.repository.ProjectRepository;
import com.taskmanagementsystem.repository.TaskRepository;
import com.taskmanagementsystem.repository.UserRepository;

@Service
public class TaskService {

	private final TaskRepository taskRepository;
	private final UserRepository userRepository;
	private final ProjectRepository projectRepository;

	public TaskService(TaskRepository taskRepository, UserRepository userRepository,
			ProjectRepository projectRepository) {

		this.taskRepository = taskRepository;
		this.userRepository = userRepository;
		this.projectRepository = projectRepository;
	}

	public Task create(TaskRequestDTO dto) {

		User user = userRepository.findById(dto.getAssignedUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		Project project = projectRepository.findById(dto.getProjectId())
				.orElseThrow(() -> new ResourceNotFoundException("Project not found"));

		Task task = new Task();

		task.setTitle(dto.getTitle());

		task.setDescription(dto.getDescription());

		task.setStatus(dto.getStatus());

		task.setPriority(dto.getPriority());

		task.setDueDate(dto.getDueDate());

		task.setAssignedUser(user);

		task.setProject(project);

		task.setCreatedAt(LocalDateTime.now());

		task.setUpdatedAt(LocalDateTime.now());

		return taskRepository.save(task);
	}

	public List<Task> getTasksByUser(Long userId) {
		return taskRepository.findByAssignedUserId(userId);
	}

	public List<Task> getTasksByStatus(TaskStatus status) {
		return taskRepository.findByStatus(status);
	}

	public List<Task> getTasksByProject(Long projectId) {

		return taskRepository.findByProjectId(projectId);
	}

	public Page<Task> search(String keyword, Pageable pageable) {

		return taskRepository.findByTitleContainingIgnoreCase(keyword, pageable);
	}

	public Page<Task> getAllWithPagination(Pageable pageable) {

		return taskRepository.findAll(pageable);
	}

	public void delete(Long id) {

		Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));

		taskRepository.delete(task);
	}

	public Task update(Long id, TaskRequestDTO dto) {

		Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));

		User user = userRepository.findById(dto.getAssignedUserId())
				.orElseThrow(() -> new RuntimeException("User not found"));

		Project project = projectRepository.findById(dto.getProjectId())
				.orElseThrow(() -> new RuntimeException("Project not found"));

		task.setTitle(dto.getTitle());
		task.setDescription(dto.getDescription());
		task.setStatus(dto.getStatus());
		task.setPriority(dto.getPriority());
		task.setDueDate(dto.getDueDate());

		task.setAssignedUser(user);
		task.setProject(project);

		task.setUpdatedAt(LocalDateTime.now());

		return taskRepository.save(task);
	}

	public List<Task> getAll() {
		return taskRepository.findAll();
	}

	public Task getById(Long id) {
		return taskRepository.getById(id);
	}
}