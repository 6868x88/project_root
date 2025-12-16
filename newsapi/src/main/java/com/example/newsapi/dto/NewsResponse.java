package com.example.newsapi.dto;

import java.time.LocalDateTime;

public class NewsResponse {
	private Long id;
	private String title;
	private String summary;
	private String url;
	private String source;
	private LocalDateTime publishedAt;


	public  NewsResponse(
			Long id,          
			String title,           
			String summary,         
			String url,           
			String source,          
			LocalDateTime publishedAt
			) {
		this.id = id;
		this.title = title;
		this.summary = summary;
		this.url = url;
		this.source = source;
		this.publishedAt = publishedAt;
	}

	public Long getId() {
		return id;
	}


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
