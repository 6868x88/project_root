package com.example.newsapi.service;

import org.springframework.stereotype.Service;

import com.example.newsapi.domain.News;
import com.example.newsapi.dto.NewsCreateRequest;
import com.example.newsapi.repository.NewsRepository;

@Service
public class NewsService {
	private final NewsRepository newsRepository;

	public NewsService(NewsRepository newsRepository) {
		this.newsRepository = newsRepository;
	}

	public void saveNews(NewsCreateRequest request) {
		News news = new News(
				request.getTitle(),
				request.getSummary(),
				request.getUrl(),
				request.getSource(),
				request.getPublishedAt()
				);

		newsRepository.save(news);
	
	}
}
