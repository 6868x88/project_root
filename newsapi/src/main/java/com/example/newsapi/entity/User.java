package com.example.newsapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, unique = true, length = 100)
	private String email;
	
	protected User() {}
	
	public User(String email) {
		this.email = email;
	}
	
	public Long getId() {return id;}
	public String getEmail() {return email;}
	
}
