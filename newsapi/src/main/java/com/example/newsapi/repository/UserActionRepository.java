package com.example.newsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.newsapi.entity.UserAction;

public interface UserActionRepository extends JpaRepository<UserAction, Long>{

}
