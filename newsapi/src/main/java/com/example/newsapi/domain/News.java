package com.example.newsapi.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String summary;

    @Column(nullable = false, length = 1000)
    private String url;

    @Column(length = 100)
    private String source;

    private LocalDateTime publishedAt;
    private LocalDateTime createdAt;

    // JPA 기본 생성자 (필수)
    protected News() {}

    // 필수 필드 생성자
    public News(String title, String summary, String url, String source, LocalDateTime publishedAt) {
        this.title = title;
        this.summary = summary;
        this.url = url;
        this.source = source;
        this.publishedAt = publishedAt;
        this.createdAt = LocalDateTime.now();
    }

    // getter
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getSummary() { return summary; }
    public String getUrl() { return url; }
    public String getSource() { return source; }
    public LocalDateTime getPublishedAt() { return publishedAt; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
