package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "textbook")
//内部設計書CL01参照
public class Textbook {
	//フィールド
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private String author;
	private Integer price;
	private Integer stock;

	@Column(name = "professor_id")
	private Integer professorId;

	@Column(name = "class_id")
	private Integer classId;

	//コンストラクタ
	public Textbook() {
	}

	public Textbook(Integer id, String title, String author, Integer price, Integer stock, Integer professorId,
			Integer classId) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.price = price;
		this.stock = stock;
		this.professorId = professorId;
		this.classId = classId;
	}

	public Textbook(String title, String author, Integer price, Integer stock, Integer professorId,
			Integer classId) {
		this.title = title;
		this.author = author;
		this.price = price;
		this.stock = stock;
		this.professorId = professorId;
		this.classId = classId;
	}

	public Textbook(Integer id, Integer stock) {
		this.id = id;
		this.stock = stock;
	}

	//ゲッター
	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public Integer getPrice() {
		return price;
	}

	public Integer getStock() {
		return stock;
	}

	public Integer getProfessorId() {
		return professorId;
	}

	public Integer getClassId() {
		return classId;
	}

}
