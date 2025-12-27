package com.example.newsapi.dto;

public class RecommendationNewsDto {
	 	private Long id;
		private String title;
	    private String summary;
	    private Integer totalScore;
	    
	    public Long getId() {
			return id;
		}

		public String getTitle() {
			return title;
		}

		public String getSummary() {
			return summary;
		}

		public Integer getTotalScore() {
			return totalScore;
		}

	    public RecommendationNewsDto(
	            Long id,
	            String title,
	            String summary,
	            Integer totalScore
	    ) {
	        this.id = id;
	        this.title = title;
	        this.summary = summary;
	        this.totalScore = totalScore;
	    }
}
