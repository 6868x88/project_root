package com.example.newsapi.dto;

import java.time.LocalDateTime;

public class NewsCreateRequest {
	private String title;
	private String summary;
	private String url;
	private String source;
	private LocalDateTime publishedAt;

	//기본 생성자
	public NewsCreateRequest() {}

	public String getTitle() {
		return title;
	}

	public String getSummary() {
		return summary;
	}

	public String getUrl() {
		return url;
	}

	public String getSource() {
		return source;
	}

	public LocalDateTime getPublishedAt() {
		return publishedAt;
	}

}
