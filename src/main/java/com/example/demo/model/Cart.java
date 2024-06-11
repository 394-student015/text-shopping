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
	public int getTotalPrice() {
		int total = 0;
		for (Textbook text : textbookList) {
			total += text.getPrice() * text.getQuantity();
		}
		return total;
	}

	//カート追加
	public void add(Textbook newItem) {
		Textbook existsTextbook = null;
		for (Textbook textbook : textbookList) {
			if (textbook.getId() == newItem.getId()) {
				existsTextbook = textbook;
				break;
			}
		}
		if (existsTextbook == null) {
			textbookList.add(newItem);
			//if (textbookList.size() >= 1) {
			int currentStock = newItem.getStock() - 1;
			newItem.setStock(currentStock);
			//}
		} else {
			existsTextbook.setQuantity(existsTextbook.getQuantity() + newItem.getQuantity());
			existsTextbook.setStock(existsTextbook.getStock() - 1);
		}
	}
	/*
	Textbook existsItem=null;
	for(Textbook item:itemList) {
		if(item.getId()==newItem.getId())
			existsItem=cart;
			break;
		}
	}
	
	if(existsItem==null)
	
	{
	cartList.add(newItem);
	}else
	{
	existsItem.setQuantity(
			existsItem.getQuantity() + newItem.getQuantity());
	}*/

	//カートから商品を削除

	public void delete(Integer textbookId) {

		for (Textbook item : textbookList) {
			if (item.getId() == textbookId) {
				textbookList.remove(item);
				break;
			}
		}
	}

	public void clear() {
		textbookList = new ArrayList<>();
	}
}
