package com.example.newsapi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.newsapi.entity.UserAction;
import com.example.newsapi.entity.UserAction.ActionType;
import com.example.newsapi.repository.UserActionRepository;

@Service
public class UserActionService {

    private final UserActionRepository repository;

    public UserActionService(UserActionRepository repository) {
        this.repository = repository;
    }

    public void save(Long userId, Long newsId, ActionType actionType) {
        UserAction action = new UserAction(userId, newsId, actionType);
        repository.save(action);
    }

    @Transactional
    public boolean likeNews(Long userId, Long newsId) {
        boolean alreadyLiked =
            repository.existsByUserIdAndNewsIdAndActionType(userId, newsId, ActionType.LIKE);

        if (alreadyLiked) return false;

        repository.save(new UserAction(userId, newsId, ActionType.LIKE));
        return true;
    }

}
