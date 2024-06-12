package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lesson")
public class Lesson {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;

	//コントローラー
	public Lesson() {
	}

	public Lesson(String name) {
		this.name = name;
	}

	public Lesson(Integer id) {
		this.id = id;
	}

	public Lesson(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	//ゲッター
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
