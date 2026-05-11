package com.taskmanagementsystem.dto;

import lombok.Data;

import java.time.LocalDate;

import com.taskmanagementsystem.enums.TaskPriority;
import com.taskmanagementsystem.enums.TaskStatus;

@Data
public class TaskDTO {

	private Long id;

	private String title;

	private String description;

	private TaskStatus status;

	private TaskPriority priority;

	private Long assignedUserId;

	private Long projectId;

	private LocalDate dueDate;
}
