package com.example.demo.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

//内部設計書CL022参照
@Component
@SessionScope
public class Member {
	private Integer id;

	//メソッド
	public Member() {
	}

	public Member(Integer id) {
		this.id = id;
	}

	//ゲッターセッター
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
