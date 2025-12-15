package com.example.newsapi.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.newsapi.dto.PythonSummaryResponseDto;


@Service
public class NewsSummaryService {

    private final RestTemplate restTemplate;

    public NewsSummaryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PythonSummaryResponseDto getNewsSummaries(int limit) {
        String url = "http://127.0.0.1:8000/news/summary?limit=" + limit;
        try {
            return restTemplate.getForObject(url, PythonSummaryResponseDto.class);
        } catch (RestClientException e) {
        	//파이썬 서버 장애일 경우
            return fallbackResponse();
        }
    }
    
    private PythonSummaryResponseDto fallbackResponse() {
        PythonSummaryResponseDto response = new PythonSummaryResponseDto();
        response.setCount(0);
        response.setArticles(List.of());
        return response;
    }

}
