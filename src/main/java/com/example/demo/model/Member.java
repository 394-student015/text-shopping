package com.example.demo.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

//内部設計書CL022参照
@Component
@SessionScope
public class Member {
	private Integer id;
	private String name;

	public Member() {

	}

	//メソッド
	//public Member(List<Account> accountSession) {
	//}

	public Member(Integer id) {
		this.id = id;
	}

	public Member(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	//ゲッターセッター
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
