package com.taskmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskmanagementsystem.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
