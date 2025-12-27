package com.example.newsapi.dto;

import com.example.newsapi.entity.UserAction.ActionType;

public class UserActionRequest {
	private Long userId;
	private Long newsId;
	private ActionType actionType;

	public Long getUserId() {
		return userId;
	}
	public Long getNewsId() {
		return newsId;
	}
	public ActionType getActionType() {
		return actionType;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}
	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

}
