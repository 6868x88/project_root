package com.example.newsapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.newsapi.dto.RecommendationNewsDto;
import com.example.newsapi.repository.NewsRepository;

@Service
public class RecommendationService {

    private final NewsRepository newsRepository;

    public RecommendationService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
        
    }

    public List<RecommendationNewsDto> recommend(Long userId, int limit) {

        List<Object[]> rows =
                newsRepository.findRecommendedNews(userId, limit);

        List<RecommendationNewsDto> result = new ArrayList<>();

        for (Object[] row : rows) {
            Long id = ((Number) row[0]).longValue();
            String title = (String) row[1];
            String summary = (String) row[2];
            Integer score = ((Number) row[3]).intValue();

            result.add(
                new RecommendationNewsDto(id, title, summary, score)
            );
        }

        return result;
    }
}
