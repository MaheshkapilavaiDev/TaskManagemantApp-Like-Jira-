package com.taskmanagementsystem.dto;

import lombok.Data;

@Data
public class AnalyticsDTO {

	private long totalProjects;

	private long totalTasks;

	private long completedTasks;

	private long pendingTasks;

	public long getTotalProjects() {
		return totalProjects;
	}

	public void setTotalProjects(long totalProjects) {
		this.totalProjects = totalProjects;
	}

	public long getTotalTasks() {
		return totalTasks;
	}

	public void setTotalTasks(long totalTasks) {
		this.totalTasks = totalTasks;
	}

	public long getCompletedTasks() {
		return completedTasks;
	}

	public void setCompletedTasks(long completedTasks) {
		this.completedTasks = completedTasks;
	}

	public long getPendingTasks() {
		return pendingTasks;
	}

	public void setPendingTasks(long pendingTasks) {
		this.pendingTasks = pendingTasks;
	}

}