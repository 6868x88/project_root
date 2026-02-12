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

    private void save(Long userId, Long newsId, ActionType actionType) {
        repository.save(new UserAction(userId, newsId, actionType));
    }
    
    public void recordClick(Long userId, Long newsId) {
        save(userId, newsId, ActionType.CLICK);
    }

    public boolean recordLike(Long userId, Long newsId) {
        boolean alreadyLiked =
            repository.existsByUserIdAndNewsIdAndActionType(
                userId, newsId, ActionType.LIKE
            );

        if (alreadyLiked) {
            return false;
        }

        save(userId, newsId, ActionType.LIKE);
        return true;
    }
    
    public void recordAction(Long userId, Long newsId, ActionType actionType) {
        if (actionType == ActionType.CLICK) {
            save(userId, newsId, ActionType.CLICK);
        } else if (actionType == ActionType.LIKE) {
            save(userId, newsId, ActionType.LIKE);
        }
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
