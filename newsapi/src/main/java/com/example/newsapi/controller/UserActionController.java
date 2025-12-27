package com.example.newsapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.newsapi.dto.UserActionRequest;
import com.example.newsapi.service.UserActionService;


@RestController
@RequestMapping("/api/actions")
public class UserActionController {

	private final UserActionService service;

	public UserActionController(UserActionService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<Void> save(
			@RequestBody UserActionRequest request) {

		service.save(
				request.getUserId(),
				request.getNewsId(),
				request.getActionType()
				);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}


