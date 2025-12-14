package com.example.newsapi.service;

import org.springframework.stereotype.Service;
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
        return restTemplate.getForObject(url, PythonSummaryResponseDto.class);
    }

}
