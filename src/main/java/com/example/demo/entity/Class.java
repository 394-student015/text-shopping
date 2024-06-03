package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "class")
//内部設計書CL04参照
public class Class {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;

	//コントローラー
	Class() {
	}

	Class(Integer id, String name) {
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
