package com.example.newsapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.newsapi.domain.News;
import com.example.newsapi.entity.User;
import com.example.newsapi.entity.UserAction.ActionType;
import com.example.newsapi.service.NewsService;
import com.example.newsapi.service.RecommendationService;
import com.example.newsapi.service.UserActionService;

import jakarta.servlet.http.HttpServletRequest;
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
    public String index(
            @RequestParam(defaultValue = "0") int page,
            Model model,
            HttpSession session
    ) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);

        Page<News> newsPage;

        if (user == null) {
            // 비로그인: 최신 6개씩 페이지네이션
            newsPage = newsService.getLatestNewsPage(page, 6);
        } else {
            // 로그인: 최신 4개씩 페이지네이션
            newsPage = newsService.getLatestNewsPage(page, 4);

            model.addAttribute(
                "favoriteNews",
                newsService.getTopPreferredNews(user.getId(), 2)
            );
        }

        model.addAttribute("newsList", newsPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute(
            "totalPages",
            Math.max(newsPage.getTotalPages(), 1)
        );

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
	public String newsDetail1(
	        @PathVariable Long id,
	        Model model,
	        HttpSession session,
	        HttpServletRequest request
	) {
	    News news = newsService.getNewsById(id);

	    User user = (User) session.getAttribute("user");
	    if (user != null) {
	        userActionService.save(user.getId(), news.getId(), ActionType.CLICK);
	    }

	    String referer = request.getHeader("Referer");
	    String backUrl = (referer != null && !referer.isBlank()) ? referer : "/";

	    model.addAttribute("news", news);
	    model.addAttribute("user", user);
	    model.addAttribute("backUrl", backUrl);

	    return "news";
	}


}
