package com.example.newsapi.dto;

import java.time.LocalDateTime;

public class NewsResponse {
	private Long id;
	private String title;
	private String summary;
	private String url;
	private String source;
	private String imageUrl;


	public  NewsResponse(
			Long id,          
			String title,           
			String summary,         
			String url,           
			String imageUrl
			) {
		this.id = id;
		this.title = title;
		this.summary = summary;
		this.url = url;
		this.imageUrl = imageUrl;
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


	public String getImageUrl() {
	    return imageUrl;
	}


}
