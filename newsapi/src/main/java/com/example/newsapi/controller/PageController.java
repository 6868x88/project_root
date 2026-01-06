package com.example.newsapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.newsapi.domain.News;
import com.example.newsapi.entity.User;
import com.example.newsapi.entity.UserAction.ActionType;
import com.example.newsapi.service.NewsService;
import com.example.newsapi.service.RecommendationService;
import com.example.newsapi.service.UserActionService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PageController {

	private final NewsService newsService;
	private final RecommendationService recommendationService;
	private final UserActionService userActionService;

    public PageController(
            NewsService newsService,
            RecommendationService recommendationService,
            UserActionService userActionService
    ) {
        this.newsService = newsService;
        this.recommendationService = recommendationService;
        this.userActionService = userActionService;
    }
    
    
    @GetMapping("/")
    public String index(Model model, HttpSession session) {

        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);

        if (user == null) {
            model.addAttribute(
                "newsList",
                newsService.getLatestNews(6)
            );
        } else {
        	 model.addAttribute(
        	            "newsList",
        	            newsService.getLatestNews(4)
        	        );
        	 
        	 model.addAttribute(
        	            "favoriteNews",
        	            newsService.getTopPreferredNews(user.getId(), 2)
        	        );
        }

        return "index";
    }

	
	@GetMapping("/recommend")
	public String recommend(Model model, HttpSession session) {

	    User user = (User) session.getAttribute("user");
	    if (user == null) {
	        return "redirect:/login";
	    }

	    model.addAttribute(
	        "recommendations",
	        recommendationService.recommend(user.getId(), 20)
	    );

	    model.addAttribute("user", user);

	    return "recommend";
	}
	
	@GetMapping("/news/{id}")
	public String newsDetail(
	        @PathVariable Long id,
	        Model model,
	        HttpSession session
	) {
	    News news = newsService.getNewsById(id);

	    User user = (User) session.getAttribute("user");
	    if (user != null) {
	        userActionService.save(
	            user.getId(),
	            news.getId(),
	            ActionType.CLICK
	        );
	    }

	    model.addAttribute("news", news);
	    model.addAttribute("user", user);

	    return "news";
	}



}
