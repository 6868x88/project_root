package com.example.newsapi.config;

import com.example.newsapi.domain.News;
import com.example.newsapi.repository.NewsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DataInitConfig {

    @Bean
    public org.springframework.boot.CommandLineRunner testInsert(NewsRepository newsRepository) {
        return args -> {
            News news = new News(
                "테스트 제목",
                "Spring JPA DB 저장 테스트입니다.",
                "http://test.com",
                "TEST",
                LocalDateTime.now()
            );

            newsRepository.save(news);
            System.out.println("✅ News 데이터 저장 완료");
        };
    }
}
