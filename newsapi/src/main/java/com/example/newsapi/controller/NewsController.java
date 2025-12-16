package com.example.newsapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.newsapi.dto.NewsCreateRequest;
import com.example.newsapi.dto.NewsResponse;
import com.example.newsapi.service.NewsService;


@RestController
@RequestMapping("/api/news")
public class NewsController {
	private final NewsService newsService;

	public NewsController(NewsService newsService) {
		this.newsService = newsService;
	}

	@PostMapping
	public ResponseEntity<String> createNews(@RequestBody NewsCreateRequest request) {

		boolean saved = newsService.saveNewsIfNotExists(request);

		//중복 기사
		if(!saved) {
			return ResponseEntity.status(409).body("Duplicate news");
		}

		return ResponseEntity.ok().build();
	}

	@GetMapping
	public ResponseEntity<List<NewsResponse>> getNews(){
		return ResponseEntity.ok(newsService.getAllNews());
	}
	
	
}
