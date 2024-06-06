package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Textbook;
import com.example.demo.repository.TextRepository;

@Controller
public class TextbookController {
	//内部設計書CL018参照
	@Autowired
	TextRepository textRepository;

	//教科書画面表示一覧
	@GetMapping("/textbook")
	public String textbook(Model model) {
		List<Textbook> textbookList = textRepository.findAll();
		model.addAttribute("textbook", textbookList);
		return "textbook";
	}

	//教科書登録画面
	@GetMapping("/textbook/add")
	public String textbookAdd(Model model) {
		return "textbookAdd";
	}

	//教科書新規登録
	@PostMapping("/textbook/add")
	public String addTextbook(
			@RequestParam(value = "title", defaultValue = "") String title,
			@RequestParam(value = "author", defaultValue = "") String author,
			@RequestParam(value = "price", defaultValue = "") Integer price,
			@RequestParam(value = "stock", defaultValue = "") Integer stock,
			@RequestParam(value = "professorId", defaultValue = "") Integer professorId,
			@RequestParam(value = "lessonId", defaultValue = "") Integer lessonId,
			Model model) {
		Textbook textbook = new Textbook(title, author, price, stock, professorId, lessonId);
		textRepository.save(textbook);
		return "redirect:/textbook";

	}

	//教科書更新画面表示
	@GetMapping("/textbook/{id}/edit")
	public String textbookEdit(
			@PathVariable("id") Integer id,
			Model model) {
		Textbook textbook = textRepository.findById(id).get();
		model.addAttribute("textbook", textbook);
		return "textbookUpdate";
	}

	//教科書更新
	@PostMapping("/textbook/{id}/edit")
	public String textbookUpdate(
			@PathVariable("id") Integer id,
			@RequestParam(value = "title", defaultValue = "") String title,
			@RequestParam(value = "author", defaultValue = "") String author,
			@RequestParam(value = "price", defaultValue = "") Integer price,
			@RequestParam(value = "stock", defaultValue = "") Integer stock,
			@RequestParam(value = "professorId", defaultValue = "") Integer professorId,
			@RequestParam(value = "lessonId", defaultValue = "") Integer lessonId,
			Model model) {
		Textbook textbook = new Textbook(id, title, author, price, stock, professorId, lessonId);
		textRepository.save(textbook);
		return "redirect:/textbook";
	}

	//教科書表示一覧
	@GetMapping("/stock")
	public String stock(Model model) {
		List<Textbook> textbook = textRepository.findAll();
		model.addAttribute("textbook", textbook);
		return "stock";
	}

	//教科書在庫表示
	@GetMapping("/textbook/{id}/stock")
	public String addStock(
			@PathVariable("id") Integer id,
			Model model) {
		Textbook textbook = textRepository.findById(id).get();
		model.addAttribute("textbook", textbook);
		return "stockAdd";

	}

	//教科書在庫追加確認画面
	@PostMapping("/textbook/{id}/stock")
	public String stockConfirm(
			@PathVariable("id") Integer id,
			@RequestParam(value = "title", defaultValue = "") String title,
			@RequestParam(value = "author", defaultValue = "") String author,
			@RequestParam(value = "price", defaultValue = "") Integer price,
			@RequestParam(value = "stock", defaultValue = "") Integer stock,
			@RequestParam(value = "professorId", defaultValue = "") Integer professorId,
			@RequestParam(value = "lessonId", defaultValue = "") Integer lessonId,
			Model model) {
		Textbook textbook = new Textbook(id, title, author, price, stock, professorId, lessonId);
		textRepository.save(textbook);
		return "redirect:/stock";
	}

	//教科書削除
	@PostMapping("/textbook/{id}/delete")
	public String deleteTextbook(
			@PathVariable("id") Integer id,
			Model model) {
		textRepository.deleteById(id);
		return "redirect:/textbook";
	}
}
