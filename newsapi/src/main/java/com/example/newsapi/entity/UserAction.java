package com.example.newsapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_actions")
public class UserAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "news_id", nullable = false)
    private Long newsId;

    @Enumerated(EnumType.STRING)
    @Column(name = "action_type", nullable = false)
    private ActionType actionType;


    protected UserAction() {
        // JPA 기본 생성자
    }

    public UserAction(Long userId, Long newsId, ActionType actionType) {
        this.userId = userId;
        this.newsId = newsId;
        this.actionType = actionType;
    }

    public enum ActionType {
        CLICK,
        LIKE
    }

}
