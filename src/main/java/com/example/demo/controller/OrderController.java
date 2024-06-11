package com.example.demo.controller;

import java.time.LocalDate;
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
import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.Textbook;
import com.example.demo.model.Cart;
import com.example.demo.model.Member;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.InformationRepository;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.TextRepository;

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
	OrderDetailRepository orderDetailRepository;

	@Autowired
	BookRepository bookRepository;
	@Autowired
	TextRepository textRepository;

	//6.注文確認画面を表示
	@PostMapping("/order/confirm")
	public String orderConfirm(
			@RequestParam(name = "coupon", defaultValue = "") Integer coupon,
			@RequestParam(name = "receive", defaultValue = "") Integer receive,
			@RequestParam(name = "payment", defaultValue = "") Integer payment,
			@RequestParam(name = "quantity", defaultValue = "") Integer quantity,
			Model model) {
		//表示のための処理
		/*List<Book> textbookList = new ArrayList();
		for (Textbook text : cart.getTextbookList()) {
		
			textbookList.add(bookRepository.findById(text.getId()).get());
		}*/
		//表示のための処理
		List<Book> textbookList = new ArrayList();
		for (Textbook text : cart.getTextbookList()) {
			//textbookList.add(bookRepository.findById(text.getId()).get());
			Book book = bookRepository.findById(text.getId()).get();
			book.setQuantity(text.getQuantity());
			textbookList.add(book);
		}

		int totalprice = cart.getTotalPrice();
		if (coupon == 2) {
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
			message2 = "無";
		} else if (coupon == 2) {
			message2 = "有";
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

		model.addAttribute("disable", "disable");

		return "orderConfirm";
	}

	@PostMapping("/order/complete")
	public String orderComplete(
			//@PathVariable("id") Integer id,
			//@RequestParam(name = "memberId", defaultValue = "") Integer memberId,
			//@RequestParam(name = "textId", defaultValue = "") Integer textId,
			@RequestParam(name = "totalprice", defaultValue = "") Integer totalprice,
			@RequestParam(name = "coupon", defaultValue = "") Integer coupon,
			@RequestParam(name = "payment", defaultValue = "") Integer payment,
			@RequestParam(name = "receive", defaultValue = "") Integer receive,
			Model model) {

		//セッションから顧客情報を持ってくる
		Integer memberId = member.getId();

		//注文情報をDBに格納する
		Information information = new Information(
				memberId,
				//textId,
				LocalDate.now(),
				totalprice,
				payment,
				receive);
		informationRepository.save(information);
		//注文詳細情報をDBに格納する
		List<Textbook> textbookList = cart.getTextbookList();
		//List<OrderDetail> orderDetails = new OrderDetail(textId, informationId, quantity);
		for (Textbook textbook : textbookList) {
			OrderDetail orderDetails = new OrderDetail(textbook.getId(),
					information.getId(),
					textbook.getQuantity());
			orderDetailRepository.save(orderDetails);
		}

		List<OrderDetail> orderDetailList = orderDetailRepository.findAllByInformationId(information.getId());
		List<Textbook> TextbookIdList = textRepository.findAll();

		for (Textbook textBook : TextbookIdList) {
			for (OrderDetail orderDetail : orderDetailList) {
				if (orderDetail.getTextId() == textBook.getId()) {
					textBook.setStock(textBook.getStock() - orderDetail.getQuantity());
					textRepository.save(textBook);
					break;
				}
			}
			//textRepository.save(textBook);
		}

		//クーポンの所持数をレポジトリから呼び出す
		Account account = accountRepository.findCouponById(memberId);

		if (coupon == 2) {
			account.setCoupon(account.getCoupon() - 1);
			accountRepository.save(account);
		}

		//合計金額５０００円以上の場合、AccountRepositoryに10%OFFクーポンを格納する
		if (receive == 2) {
			if (totalprice - 880 >= 5000) {
				//乱数生成
				Random rand = new Random();
				int num = rand.nextInt(5);

				if (num == 0) {
					//クーポン所持数を1枚増やす
					account.setCoupon(account.getCoupon() + 1);
					accountRepository.save(account);
					//あたりを返す
					//model.addAttribute("result", "次回から使える10%OFFクーポンゲット！");
					model.addAttribute("win", "win");
				} else {
					//はずれを返す	
					//model.addAttribute("result", "残念！");
					model.addAttribute("lose", "lose");
				}
			}
		} else {
			if (totalprice >= 5000) {
				//乱数生成
				Random rand = new Random();
				int num = rand.nextInt(5);

				if (num == 0) {
					//クーポン所持数を1枚増やす
					account.setCoupon(account.getCoupon() + 1);
					accountRepository.save(account);
					//あたりを返す
					//model.addAttribute("result", "次回から使える10%OFFクーポンゲット！");
					model.addAttribute("win", "win");
				} else {
					//はずれを返す
					//model.addAttribute("result", "残念！");
					model.addAttribute("lose", "lose");
				}
			}

		}

		//セッションスコープのカート情報を削除する
		cart.clear();

		//注文完了画面に戻すための購入IDを設定する
		model.addAttribute("information", information);

		return "orderComplete";
	}

}
