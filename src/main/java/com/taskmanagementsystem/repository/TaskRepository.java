package com.taskmanagementsystem.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

import com.taskmanagementsystem.entity.Task;
import com.taskmanagementsystem.enums.TaskStatus;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	List<Task> findByProjectId(Long projectId);

	List<Task> findByAssignedUserId(Long userId);

	List<Task> findByStatus(TaskStatus status);

	Page<Task> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);
}
