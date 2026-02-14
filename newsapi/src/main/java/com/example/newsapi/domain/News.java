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


    private LocalDateTime createdAt;
    
    @Column(name = "image_url", length = 500)
    private String imageUrl;

    // JPA 기본 생성자 (필수)
    protected News() {}

    // 필수 필드 생성자
    public News(String title,
    	    String summary,
    	    String url,
    	    String imageUrl
    	    ) {
        this.title = title;
        this.summary = summary;
        this.url = url;
        this.imageUrl = imageUrl;
        this.createdAt = LocalDateTime.now();
    }

    // getter
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getSummary() { return summary; }
    public String getUrl() { return url; }
    public String getImageUrl() { return imageUrl;}
    public LocalDateTime getCreatedAt() { return createdAt; }
}
