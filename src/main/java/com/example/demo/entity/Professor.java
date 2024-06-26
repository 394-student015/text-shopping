package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "professor")

public class Professor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String major;

	//コンストラクタ
	public Professor() {
	}

	public Professor(String name, String major) {
		this.name = name;
		this.major = major;
	}

	public Professor(Integer id, String name, String major) {
		this.id = id;
		this.name = name;
		this.major = major;
	}

	//ゲッター
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getMajor() {
		return major;
	}
}
