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
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.TextRepository;

@Controller
public class CartController {

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

	@Autowired
	OrderDetailRepository orderDetailrepository;

	//教科書一覧表示
	@GetMapping("/shopMenu")
	public String shopMenu(
			@RequestParam(name = "title", defaultValue = "") String title,
			@RequestParam(name = "professor", defaultValue = "") String professor,
			@RequestParam(name = "lecture", defaultValue = "") String lecture,
			Model model) {

		//教科書一覧情報の取得
		List<Book> textbookList = bookRepository.findAll();
		List<Book> bookListbrowse = new ArrayList<>();
		for (Book book : textbookList) {
			for (Textbook textbook : cart.getTextbookList()) {
				if (book.getId() == textbook.getId()) {
					book.setStock(textbook.getStock());

					break;
				}
			}

			bookListbrowse.add(book);

		}

		// 部分一致検索
		if (title.length() > 0) { // 書名
			bookListbrowse = bookRepository.findByTitleContaining(title);
		} else if (professor.length() > 0) { // 教授名
			bookListbrowse = bookRepository.findByProfessorContaining(professor);
		} else if (lecture.length() > 0) { // 授業名
			bookListbrowse = bookRepository.findByLectureContaining(lecture);
		} else { // 全商品
			bookListbrowse = bookRepository.findAll();
		}

		if (bookListbrowse.size() == 0) {
			model.addAttribute("error", "該当するデータはありません");
		}

		model.addAttribute("title", title);
		model.addAttribute("professor", professor);
		model.addAttribute("lecture", lecture);
		model.addAttribute("textbookList", bookListbrowse);
		return "shopMenu";
	}

	//カート内容表示
	@GetMapping("/cart")
	public String index(Model model) {
		List<Book> textbookList = new ArrayList();
		for (Textbook text : cart.getTextbookList()) {
			//textbookList.add(bookRepository.findById(text.getId()).get());
			Book book = bookRepository.findById(text.getId()).get();
			book.setQuantity(text.getQuantity());
			textbookList.add(book);
		}
		model.addAttribute("textbookList", textbookList);
		return "cart";
	}

	//カートに追加処理
	@PostMapping("/cart/add")
	public String addCart(
			@RequestParam(name = "textbookId") Integer textbookId,
			@RequestParam(name = "quantity", defaultValue = "1") Integer quantity,
			Model model) {

		Textbook textbook = textRepository.findById(textbookId).get();

		textbook.setQuantity(quantity);
		cart.add(textbook);

		//表示のための処理
		List<Book> textbookList = new ArrayList();
		for (Textbook text : cart.getTextbookList()) {
			//textbookList.add(bookRepository.findById(text.getId()).get());
			Book book = bookRepository.findById(text.getId()).get();
			book.setQuantity(text.getQuantity());
			textbookList.add(book);
		}
		model.addAttribute("textbookList", textbookList);
		Integer accountId = member.getId();
		List<Account> accountList = accountRepository.findAllById(accountId);

		Account account = accountRepository.findCouponById(member.getId());
		//String couponerror;
		if (account.getCoupon() <= 0) {
			//クーポンの選択肢をCSSのnoneで消す
			model.addAttribute("accountList", accountList);
			model.addAttribute("invisible", "invisible");
			return "cart";
		} else {
			model.addAttribute("accountList", accountList);
			return "cart";
		}
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