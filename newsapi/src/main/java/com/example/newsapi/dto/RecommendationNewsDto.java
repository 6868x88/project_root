package com.example.newsapi.dto;

public class RecommendationNewsDto {
	 	private Long id;
	 	private String image_Url;
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
		 public String getImageUrl() {
		        return image_Url;
		    }

	    public RecommendationNewsDto(
	            Long id,
	            String title,
	            String summary,
	            String image_Url,
	            Integer totalScore
	    ) {
	        this.id = id;
	        this.title = title;
	        this.summary = summary;
	        this.image_Url = image_Url;
	        this.totalScore = totalScore;
	    }
}
