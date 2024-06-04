package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Textbook;
import com.example.demo.model.Cart;
import com.example.demo.repository.TextRepository;

@Controller
public class CartController {
	//内部設計書CL015参照
	@Autowired
	Cart cart;

	@Autowired
	TextRepository textRepository;

	//教科書一覧表示
	@GetMapping("/shopMenu")
	public String shopMenu(

			Model model) {
		List<Textbook> textbookList = textRepository.findAll();
		model.addAttribute("textbook", textbookList);

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
			@RequestParam(name = "id", defaultValue = "") Integer id,

			Model model) {

		Textbook textbook = textRepository.findById(id).get();
		cart.add(textbook);

		return "redirect:/cart";
	}
}
