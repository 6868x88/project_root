package com.example.newsapi.dto;

import java.util.List;

public class PythonSummaryResponseDto {
	   private int count;
	    private List<ArticleSummaryDto> articles;

	    public int getCount() { return count; }
	    public void setCount(int count) { this.count = count; }

	    public List<ArticleSummaryDto> getArticles() { return articles; }
	    public void setArticles(List<ArticleSummaryDto> articles) {
	        this.articles = articles;
	    }
}
