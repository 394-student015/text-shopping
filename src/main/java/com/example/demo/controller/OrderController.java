package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {
	//内部設計書CL014参照
	//6.注文確認画面を表示
	@GetMapping("/order")
	public String order() {
		return "orderConfirm";
	}

	@PostMapping("/order/confirm")
		public String orderConfirm(
				@RequestParam(name="text_id", defaultValue = "")Integer text_id,
				@RequestParam(name="title", defaultValue = "")String title,
				@RequestParam(name="", defaultValue = ""),
				@RequestParam(name="", defaultValue = ""),
				) {
		return "orderConfirm";
	}

	//注文する
	//会員情報をDBに格納する
	//注文情報をDBに格納する
	//注文詳細情報をDBに格納する
	//
	//セッションスコープのカート情報をクリアする
	//注文完了画面に返却する購入IDを設定する
}
