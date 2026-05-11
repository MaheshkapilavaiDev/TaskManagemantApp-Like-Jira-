package com.taskmanagementsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanagementsystem.dto.AnalyticsDTO;
import com.taskmanagementsystem.service.AnalyticsService;

@RestController
public class AnalyticsController {

	private final AnalyticsService service;

	public AnalyticsController(AnalyticsService service) {
		this.service = service;
	}

	@GetMapping("/analytics/dashboard")
	public AnalyticsDTO dashboard() {
		return service.dashboard();
	}
}
