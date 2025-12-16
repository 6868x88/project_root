package com.example.newsapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.newsapi.domain.News;
import com.example.newsapi.dto.NewsCreateRequest;
import com.example.newsapi.dto.NewsResponse;
import com.example.newsapi.repository.NewsRepository;

@Service
public class NewsService {
	private final NewsRepository newsRepository;

	public NewsService(NewsRepository newsRepository) {
		this.newsRepository = newsRepository;
	}

	public boolean saveNewsIfNotExists(NewsCreateRequest request) {

		if(newsRepository.existsByUrl(request.getUrl())) {
			return false;
		}

		News news = new News(
				request.getTitle(),
				request.getSummary(),
				request.getUrl(),
				request.getSource(),
				request.getPublishedAt()
				);

		newsRepository.save(news);
		return true;
	}

	public List<NewsResponse> getAllNews() {
		return newsRepository.findAll().stream()
				.map(news -> new NewsResponse(
						news.getId(),
						news.getTitle(),
						news.getSummary(),
						news.getUrl(),
						news.getSource(),
						news.getPublishedAt()
						))
				.collect(Collectors.toList());
	}
}
