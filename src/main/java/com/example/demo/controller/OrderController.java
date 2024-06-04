package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Account;

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
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "address", defaultValue = "") String address,
			@RequestParam(name = "tel", defaultValue = "") String tel,
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "password", defaultValue = "") String password,
			@RequestParam(name = "coupon", defaultValue = "") Integer coupon,
			//			@RequestParam(name = "textId", defaultValue = "") Integer textId,
			//			@RequestParam(name = "title", defaultValue = "") String title,
			//			@RequestParam(name = "stock", defaultValue = "") Integer stock,
			//			@RequestParam(name = "className", defaultValue = "") String className,
			//			@RequestParam(name = "proName", defaultValue = "") String proName,
			//			@RequestParam(name = "major", defaultValue = "") String major,
			Model model) {
		//顧客情報
		Account account = new Account(name, address, tel, email, password, coupon);
		model.addAttribute("account", account);

		//購入IDをもとに、カート情報と会員情報を登録する

		//セッションスコープのcartを取得する
		//カートに追加された商品を登録する
		//		List<Information> information = new ArrayList<>();
		//		for(Information information : getTextbookList) {
		//			
		//		}
		//		
		//		infomationRepository.saveAll()
		//合計金額５０００円以上の場合、AccountRepositoryに10%OFFクーポンを格納する

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
