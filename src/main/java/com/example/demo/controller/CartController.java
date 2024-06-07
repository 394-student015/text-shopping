package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Account;
import com.example.demo.entity.Book;
import com.example.demo.entity.Textbook;
import com.example.demo.model.Cart;
import com.example.demo.model.Member;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.TextRepository;

@Controller
public class CartController {
	//内部設計書CL015参照
	@Autowired
	Cart cart;

	@Autowired
	Member member;

	@Autowired
	TextRepository textRepository;

	@Autowired
	BookRepository bookRepository;

	@Autowired
	AccountRepository accountRepository;

	//教科書一覧表示
	@GetMapping("/shopMenu")
	public String shopMenu(
			@RequestParam(name = "keyword", defaultValue = "") String keyword,
			Model model) {

		//教科書一覧情報の取得
		List<Book> textbookList = bookRepository.findAll();

		if (keyword.length() > 0) {
			// 商品名による部分一致検索
			textbookList = bookRepository.findByTitleContaining(keyword);
		} else {
			// 全商品検索
			textbookList = bookRepository.findAll();
		}

		model.addAttribute("keyword", keyword);
		model.addAttribute("textbookList", textbookList);

		return "shopMenu";
	}

	//カート内容表示
	@GetMapping("/cart")
	public String index() {

		return "cart";
	}

	//カートに追加処理
	@PostMapping("/cart/add")
	public String addCart(
			@RequestParam(name = "textbookId") Integer textbookId,
			Model model) {

		Textbook textbook = textRepository.findById(textbookId).get();
		cart.add(textbook);

		//表示のための処理
		List<Book> textbookList = new ArrayList();
		for (Textbook text : cart.getTextbookList()) {
			textbookList.add(bookRepository.findById(text.getId()).get());
		}
		model.addAttribute("textbookList", textbookList);
		Integer accountId = member.getId();
		List<Account> accountList = accountRepository.findAllById(accountId);
		model.addAttribute("accountList", accountList);
		return "cart";
	}

	//指定した商品をカートから削除
	@PostMapping("/cart/delete")
	public String deleteCart(
			@RequestParam(name = "textbookId") Integer textbookId,
			Model model) {

		cart.delete(textbookId);

		List<Book> textbookList = new ArrayList();
		for (Textbook text : cart.getTextbookList()) {

			textbookList.add(bookRepository.findById(text.getId()).get());

		}
		model.addAttribute("textbookList", textbookList);

		return "cart";
	}

}