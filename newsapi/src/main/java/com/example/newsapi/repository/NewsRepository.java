package com.example.newsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.newsapi.domain.News;

public interface NewsRepository extends JpaRepository<News, Long> {
	boolean existsByUrl(String url);
	
	
}
