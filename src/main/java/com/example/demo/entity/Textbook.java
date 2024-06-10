package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "textbook")
//内部設計書CL01参照
public class Textbook {
	//フィールド
	//@OrderBy(value = "id asc")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private String author;
	private Integer price;
	private Integer stock;

	@Transient //永続化対象外
	private Integer quantity;

	@Column(name = "professor_id")
	private Integer professorId;

	@Column(name = "lesson_id")
	private Integer lessonId;

	//コンストラクタ
	public Textbook() {
	}

	public Textbook(Integer id, String title, String author, Integer price, Integer stock, Integer professorId,
			Integer lessonId) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.price = price;
		this.stock = stock;
		this.professorId = professorId;
		this.lessonId = lessonId;
	}

	public Textbook(String title, String author, Integer price, Integer stock, Integer professorId,
			Integer lessonId) {
		this.title = title;
		this.author = author;
		this.price = price;
		this.stock = stock;
		this.professorId = professorId;
		this.lessonId = lessonId;
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

	public Integer getLessonId() {
		return lessonId;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
