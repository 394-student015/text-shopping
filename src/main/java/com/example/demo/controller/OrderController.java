package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Account;
import com.example.demo.model.Cart;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.InformationRepository;

@Controller
public class OrderController {
	//内部設計書CL014参照

	@Autowired
	Cart cart;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	InformationRepository informationRepository;

	//6.注文確認画面を表示
	@GetMapping("/order")
	public String order() {
		return "orderConfirm";
	}

	@PostMapping("/order/confirm")
	public String orderConfirm(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "textId", defaultValue = "") Integer textId,
			@RequestParam(name = "title", defaultValue = "") String title,
			@RequestParam(name = "stock", defaultValue = "") Integer stock,
			@RequestParam(name = "className", defaultValue = "") String className,
			@RequestParam(name = "proName", defaultValue = "") String proName,
			@RequestParam(name = "major", defaultValue = "") String major,
			@RequestParam(name = "totalPrice", defaultValue = "") Integer totalprice,
			@RequestParam(name = "receive", defaultValue = "") Integer receive,
			Model model) {

		//顧客情報をまとめる？
		List<Account> informationList = accountRepository.findByName(name);

		//セッションスコープのcartを取得する
		//カートに追加された商品を登録する

		//注文情報をDBに格納する
		//		Information information = new Information(
		//				information.getId(),
		//				information.getMemberId(),
		//				information.getTextId(),
		//				information.getDate(),
		//				information.getTotalprice(),
		//				information.getPayment(),
		//				information.getReceive());
		//		informationRepository.save();

		//		List<Textbook> textbookList = cart.getTextbookList();
		//		List<Information> information2 = new ArrayList<>();
		//		for (Information informationElement : textbookList) {
		//			information.add(
		//				new Information(
		//					information.getId(),
		//					textbook.getId(),
		//				)
		//			;
		//		}

		//		informationRepository.saveAll();

		//合計金額５０００円以上の場合、AccountRepositoryに10%OFFクーポンを格納する
		//		if (information.getTotalprice() >= 5000) {
		//
		//		}

		//セッションスコープのカート情報を削除する
		//		cart.clear();

		//注文完了画面に戻すための購入IDを設定する
		//		model.addAttribute("orderNumber", information.getId());

		return "orderComplete";
	}

}
