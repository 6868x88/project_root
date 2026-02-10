package com.example.newsapi.controller;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.newsapi.dto.NewsCreateRequest;
import com.example.newsapi.dto.NewsResponse;
import com.example.newsapi.dto.RecommendationNewsDto;
import com.example.newsapi.service.NewsService;
import com.example.newsapi.service.RecommendationService;


@RestController
@RequestMapping("/api/news")
public class NewsController {
	private final NewsService newsService;
	private final RecommendationService recommendationService;

	public NewsController(
			NewsService newsService
			,RecommendationService recommendationService) {
		this.newsService = newsService;
		this.recommendationService = recommendationService;
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
	public ResponseEntity<Page<NewsResponse>> getNews(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size
			) {
		return ResponseEntity.ok(newsService.getNewsPage(page, size));
	}



	@GetMapping("/recommend")
	public ResponseEntity<List<RecommendationNewsDto>> recommend(
			@RequestParam Long userId,
			@RequestParam(defaultValue = "20") int limit
			) {
		return ResponseEntity.ok(
				recommendationService.recommend(userId, limit)
				);
	}
	



}
