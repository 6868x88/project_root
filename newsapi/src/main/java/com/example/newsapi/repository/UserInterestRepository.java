package com.example.newsapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.newsapi.entity.UserInterest;

public interface UserInterestRepository extends JpaRepository<UserInterest, Long>{
	List<UserInterest> findByUserId(Long userId);
}
