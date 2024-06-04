package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.example.demo.entity.Textbook;

//内部設計書CL021参照
@Component
@SessionScope
public class Cart {

	//教科書リスト
	private List<Textbook> textbookList = new ArrayList<>();

	//教科書リストのゲッター
	public List<Textbook> getTextbookList() {
		return textbookList;
	}

	//合計金額ゲッター
	public int add() {
		int total = 0;
		for (Textbook text : textbookList) {
			total += text.getPrice() * text.getPrice();
		}
		return total;
	}

	/*//カート追加
	public void add(Cart newCart) {
		Cart existsCart=null;
		for(Cart cart:textbookList) {
			if(cart.getId()==newCart.getId())
				
		}
	}*/
}
