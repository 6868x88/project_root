package com.example.newsapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.newsapi.domain.News;
import com.example.newsapi.dto.NewsCreateRequest;
import com.example.newsapi.dto.NewsResponse;
import com.example.newsapi.entity.UserInterest;
import com.example.newsapi.repository.NewsRepository;
import com.example.newsapi.repository.UserInterestRepository;


@Service
public class NewsService {
	private final NewsRepository newsRepository;
	private UserInterestRepository userInterestRepository;

	public NewsService(
			NewsRepository newsRepository
			,UserInterestRepository userInterestRepository) {
		this.newsRepository = newsRepository;
		this.userInterestRepository = userInterestRepository;
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
				request.getImageUrl(),
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
						news.getImageUrl(),
						news.getPublishedAt()
						))
				.collect(Collectors.toList());
	}

	public List<News> getLatestNews(int limit) {
		return newsRepository.findTop10ByOrderByPublishedAtDesc();
	}

	public News getNewsById(Long id) {
		return newsRepository.findById(id)
				.orElseThrow(() ->
				new IllegalArgumentException("News not found: " + id)
						);
	}

	public Page<NewsResponse> getNewsPage(int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("publishedAt").descending());

		return newsRepository
				.findAll(pageable)
				.map(news -> new NewsResponse(
						news.getId(),
						news.getTitle(),
						news.getSummary(),
						news.getUrl(),
						news.getSource(),
						news.getImageUrl(),
						news.getPublishedAt()
						));
	}
	//	
	public Page<NewsResponse> getRecommendedNews(
			Long userId,
			int page,
			int size
			) {
		List<UserInterest> interests =
				userInterestRepository.findByUserId(userId);

		if (interests.isEmpty()) {
			return getNewsPage(page, size); // fallback
		}

		Pageable pageable = PageRequest.of(
				page, size, Sort.by("publishedAt").descending()
				);

		// 일단 첫 관심사 기준 (단순 & 명확)
		String keyword = interests.get(0).getKeyword();

		return newsRepository
				.findByTitleContainingOrSummaryContaining(
						keyword, keyword, pageable
						)
				.map(news -> new NewsResponse(
						news.getId(),
						news.getTitle(),
						news.getSummary(),
						news.getUrl(),
						news.getSource(),
						news.getImageUrl(),
						news.getPublishedAt()
						));
	}

}
