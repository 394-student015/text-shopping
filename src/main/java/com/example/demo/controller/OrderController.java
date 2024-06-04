package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Account;
import com.example.demo.entity.Information;
import com.example.demo.entity.Textbook;
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
			@RequestParam(name = "totalPrice", defaultValue = "") Integer totalPrice,
			@RequestParam(name = "receive", defaultValue = "") Integer receive,
			Model model) {

		//顧客情報をまとめる？
		List<Account> informationList = accountRepository.findByName(name);

		//セッションスコープのcartを取得する
		//カートに追加された商品を登録する

		//注文情報をDBに格納する
		Information information = new Information(informationList, textId, title, stock, className,
				proName, major, totalPrice, receive);
		informationRepository.save(information);

		List<Textbook> textbookList = cart.getTextbookList();
		List<Information> information2 = new ArrayList<>();
		//		information2.add(textId);
		//		information2.add();
		//		for (Textbook informationElement : textbookList) {
		//			
		//		}

		//		informationRepository.saveAll();

		//クーポンの所持数をレポジトリから呼び出す
		List<Account> account = accountRepository.findByName(name);

		//10%OFFクーポンの所持数が1枚以上である場合、合計金額から10％割引する
		//初期化
		int newCoupon = 0;
		int newTotalprice = 0;

		//合計金額５０００円以上の場合、AccountRepositoryに10%OFFクーポンを格納する
		if (information.getTotalprice() >= 5000) {
			//乱数生成
			Random rand = new Random();
			int num = rand.nextInt(5);

			if (num == 0) {
				//クーポン所持数を1枚増やす
				newCoupon = ((Account) account).getCoupon() + 1;
				//割引された合計金額をエンティティにセットする
				((Account) account).setCoupon(newCoupon);
			}
		}

		if (((Account) account).getCoupon() > 0) { // クーポン所持数1枚以上
			//合計金額から10％割引する
			newTotalprice = (int) (information.getTotalprice() * 0.9);
			information.setTotalprice(newTotalprice);
			//クーポン所持数を1枚減らす
			newCoupon = ((Account) account).getCoupon() - 1;
			//割引された合計金額をエンティティにセットする
			((Account) account).setCoupon(newCoupon);
		}

		//セッションスコープのカート情報を削除する
		cart.clear();

		//注文完了画面に戻すための購入IDを設定する
		model.addAttribute("orderNumber", information.getId());

		return "orderComplete";
	}

}
