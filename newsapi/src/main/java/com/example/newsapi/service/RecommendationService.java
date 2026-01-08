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
            String image_Url = (String) row[3];
            Number scoreNum = (Number) row[4];
            int score = scoreNum != null ? scoreNum.intValue() : 0;

            result.add(
                new RecommendationNewsDto(id, title, summary, image_Url, score)
            );
        }

        return result;
    }
}
