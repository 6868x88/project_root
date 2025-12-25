package com.example.newsapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_interest")
public class UserInterest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false, length = 50)
    private String keyword;

    protected UserInterest() {
        // JPA 기본 생성자
    }

    public UserInterest(Long userId, String keyword) {
        this.userId = userId;
        this.keyword = keyword;
    }

    public Long getUserId() {
        return userId;
    }

    public String getKeyword() {
        return keyword;
    }
}
