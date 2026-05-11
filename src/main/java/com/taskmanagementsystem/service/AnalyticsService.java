package com.taskmanagementsystem.service;

import org.springframework.stereotype.Service;

import com.taskmanagementsystem.dto.AnalyticsDTO;
import com.taskmanagementsystem.enums.TaskStatus;
import com.taskmanagementsystem.repository.ProjectRepository;
import com.taskmanagementsystem.repository.TaskRepository;

@Service
public class AnalyticsService {

	private final TaskRepository taskRepository;
	private final ProjectRepository projectRepository;

	public AnalyticsService(TaskRepository taskRepository, ProjectRepository projectRepository) {

		this.taskRepository = taskRepository;
		this.projectRepository = projectRepository;
	}

	public AnalyticsDTO dashboard() {

		AnalyticsDTO dto = new AnalyticsDTO();

		long totalProjects = projectRepository.count();

		long totalTasks = taskRepository.count();

		long completedTasks = taskRepository.findByStatus(TaskStatus.DONE).size();

		long pendingTasks = taskRepository.findByStatus(TaskStatus.OPEN).size();

		dto.setTotalProjects(totalProjects);
		dto.setTotalTasks(totalTasks);
		dto.setCompletedTasks(completedTasks);
		dto.setPendingTasks(pendingTasks);

		return dto;
	}
}