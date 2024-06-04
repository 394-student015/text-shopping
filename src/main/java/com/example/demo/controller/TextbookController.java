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

	//教科書新規登録
	@PostMapping("/textbook")
	public String addTextbook(
			@RequestParam(value = "title", defaultValue = "") String title,
			@RequestParam(value = "author", defaultValue = "") String author,
			@RequestParam(value = "price", defaultValue = "") Integer price,
			@RequestParam(value = "stock", defaultValue = "") Integer stock,
			@RequestParam(value = "professorId", defaultValue = "") String professorId,
			@RequestParam(value = "classId", defaultValue = "") String classId,
			Model model) {
		Textbook textbook = new Textbook();
		textRepository.save(textbook);
		return "professorAdd";

	}

	//教科書在庫表示
	@PostMapping("/textbook/{id}/stock")
	public String stock(
			@PathVariable("id") Integer id,
			Model model) {
		Textbook textbook = textRepository.findById(id).get();
		model.addAttribute("textbook", textbook);
		return "textbookUpdate";

	}

	//教科書在庫追加
	@PostMapping("/textbook/{id}/edit")
	public String addStock(
			@PathVariable("id") Integer id,
			@RequestParam(name = "stock", defaultValue = "") Integer stock,
			Model model) {
		Textbook textbook = new Textbook(id, stock);
		textRepository.save(textbook);
		return "redirect:/textbook";
	}

	/*教科書在庫追加確認画面
	@PostMapping()
	public String stockConfirm() {
	
	}
	*/

	//教科書削除
	@PostMapping("/textbook/{id}/delete")
	public String deleteTextbook(
			@PathVariable("id") Integer id,
			Model model) {
		textRepository.deleteById(id);
		return "redirect:/professor";
	}
}
