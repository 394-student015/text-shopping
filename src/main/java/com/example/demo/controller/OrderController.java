package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Account;
import com.example.demo.entity.Book;
import com.example.demo.entity.Information;
import com.example.demo.entity.Textbook;
import com.example.demo.model.Cart;
import com.example.demo.model.Member;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.InformationRepository;

@Controller
public class OrderController {
	//内部設計書CL014参照
	@Autowired
	Member member;

	@Autowired
	Cart cart;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	InformationRepository informationRepository;

	@Autowired
	BookRepository bookRepository;

	//6.注文確認画面を表示
	@PostMapping("/order/confirm")
	public String orderConfirm(
			@RequestParam(name = "coupon", defaultValue = "") Integer coupon,
			@RequestParam(name = "receive", defaultValue = "") Integer receive,
			@RequestParam(name = "payment", defaultValue = "") Integer payment,
			Model model) {
		//表示のための処理
		List<Book> textbookList = new ArrayList();
		for (Textbook text : cart.getTextbookList()) {

			textbookList.add(bookRepository.findById(text.getId()).get());

		}
		int totalprice = cart.getTotalPrice();
		if (coupon == 1) {
			totalprice = (int) (totalprice * 0.9);
		}

		String message1 = null;
		if (receive == 1) {
			message1 = "店舗受け取り";
		} else if (receive == 2) {
			totalprice += 880;
			message1 = "配送";
		}

		String message2 = null;
		if (coupon == 1) {
			message2 = "有";
		} else if (coupon == 2) {
			message2 = "無";
		}

		String message3 = null;
		if (payment == 1) {
			message3 = "現金";
		} else if (payment == 2) {
			message3 = "クレジットカード";
		}

		model.addAttribute("textbookList", textbookList);
		model.addAttribute("receive", receive);
		model.addAttribute("message1", message1);
		model.addAttribute("coupon", coupon);
		model.addAttribute("message2", message2);
		model.addAttribute("payment", payment);
		model.addAttribute("message3", message3);
		model.addAttribute("totalprice", totalprice);

		return "orderConfirm";
	}

	@PostMapping("/order/complete")
	public String orderComplete(
			//@PathVariable("id") Integer id,
			@RequestParam(name = "memberId", defaultValue = "") Integer memberId,
			//@RequestParam(name = "textId", defaultValue = "") String textId,
			@RequestParam(name = "totalprice", defaultValue = "") Integer totalprice,
			@RequestParam(name = "receive", defaultValue = "") Integer receive,
			@RequestParam(name = "coupon", defaultValue = "") Integer coupon,
			@RequestParam(name = "payment", defaultValue = "") Integer payment,
			Model model) {

		//顧客情報をまとめる？
		//List<Account> informationList = accountRepository.findAll();

		//セッションスコープのcartを取得する
		//カートに追加された商品を登録する

		//注文情報をDBに格納する
		Information information = new Information();

		informationRepository.save(information);

		List<Textbook> textbookList = cart.getTextbookList();
		List<Information> information2 = new ArrayList<>();

		/*information2.add(textId);
		information2.add();
		for (Textbook informationElement : textbookList) {
		
		}
		
		
		informationRepository.saveAll();
		*/

		//クーポンの所持数をレポジトリから呼び出す
		Account account = accountRepository.findById(memberId).get();

		//int currentCoupon = account.getCoupon();

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
				newCoupon = account.getCoupon() + 1;
				//割引された合計金額をエンティティにセットする
				account.setCoupon(newCoupon);
			}
		}

		if (account.getCoupon() > 0) { // クーポン所持数1枚以上
			//合計金額から10％割引する
			newTotalprice = (int) (information.getTotalprice() * 0.9);
			information.setTotalprice(newTotalprice);
			//クーポン所持数を1枚減らす
			newCoupon = account.getCoupon() - 1;
			//割引された合計金額をエンティティにセットする
			account.setCoupon(newCoupon);
		}

		//セッションスコープのカート情報を削除する
		cart.clear();

		//注文完了画面に戻すための購入IDを設定する
		model.addAttribute("orderNumber", information.getId());

		return "orderComplete";
	}

}
