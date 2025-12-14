package com.example.newsapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.newsapi.dto.PythonSummaryResponseDto;
import com.example.newsapi.service.NewsSummaryService;


@RestController
public class NewsController {
	private final NewsSummaryService service;

	public NewsController(NewsSummaryService service) {
		this.service = service;
	}

	@GetMapping("/api/news")
	public PythonSummaryResponseDto getNews(
			@RequestParam(defaultValue = "5") int limit) {
		return service.getNewsSummaries(limit);
	}

}
