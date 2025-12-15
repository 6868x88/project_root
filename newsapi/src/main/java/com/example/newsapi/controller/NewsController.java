package com.example.newsapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.newsapi.dto.NewsCreateRequest;
import com.example.newsapi.service.NewsService;


@RestController
@RequestMapping("/api/news")
public class NewsController {
	private final NewsService newsService;

//	public NewsController(NewsSummaryService service) {
//		this.service = service;
//	}
//
//	@GetMapping("/api/news")
//	public PythonSummaryResponseDto getNews(
//			@RequestParam(defaultValue = "5") int limit) {
//		return service.getNewsSummaries(limit);
//	}

	
	public NewsController(NewsService newsService) {
		this.newsService = newsService;
	}
	
	   @PostMapping
	    public ResponseEntity<Void> createNews(@RequestBody NewsCreateRequest request) {
	        newsService.saveNews(request);
	        return ResponseEntity.ok().build();
	    }
}
