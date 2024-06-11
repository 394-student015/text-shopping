package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Professor;
import com.example.demo.repository.ProfessorRepository;

@Controller
public class ProfessorController {
	//内部設計書CL019参照
	@Autowired
	ProfessorRepository professorRepository;

	//教授画面一覧表示
	@GetMapping("/professor")
	public String professor(Model model) {
		List<Professor> professorList = professorRepository.findAll();
		model.addAttribute("professor", professorList);
		return "professor";
	}

	//教授登録画面表示
	@GetMapping("/professor/add")
	public String addProfessor(Model model) {

		Professor professor = new Professor();
		model.addAttribute("professor", professor);
		return "professorAdd";
	}

	//教授新規追加
	@PostMapping("/professor/add")
	public String professorAdd(
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "major", defaultValue = "") String major,
			Model model) {

		//エラーチェック、ここから
		List<String> messages = new ArrayList<String>();
		if (name == null || name.length() == 0) {
			messages.add("教授名は必須です");
		}
		if (major == null || major.length() == 0) {
			messages.add("専攻は必須です");
		}
		//エラーチェック、ここまで

		//エラー有無に関わらずインスタンス化する
		Professor professor = new Professor(name, major);
		if (messages.size() >= 1) {
			//エラーがあった場合
			model.addAttribute("message", messages);
			//以下があることで、エラー返したときに入力したものが入力欄に残る
			model.addAttribute("professor", professor);
			return "professorAdd";
		}

		//エラーがなかった場合
		professorRepository.save(professor);
		return "redirect:/professor";
	}

	//教授更新画面表示
	@GetMapping("/professor/{id}/edit")
	public String updateProfessor(
			@PathVariable("id") Integer id,
			Model model) {
		Professor professor = professorRepository.findById(id).get();
		model.addAttribute("professor", professor);
		return "professorUpdate";
	}

	//教授更新
	@PostMapping("/professor/{id}/edit")
	public String updateProfessor(
			@PathVariable("id") Integer id,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "major", defaultValue = "") String major,
			Model model) {

		//エラーメッセージ表示、ここから
		List<String> messages = new ArrayList<String>();
		if (name == null || name.length() == 0) {
			messages.add("教授名は必須です");
		}
		if (major == null || major.length() == 0) {
			messages.add("専攻は必須です");
		}

		/*Professor messageList = new Professor(name, major);
		model.addAttribute("message", messageList);*/
		//エラーメッセージ表示
		/*List<String> messages = new ArrayList<String>();
		if (name == null || name.length() == 0) {
			messages.add("教授名は必須です");
		}
		if (major == null || major.length() == 0) {
			messages.add("専攻は必須です");
		}
		
		Professor messageList = new Professor(name, major);
		model.addAttribute("message", messageList);
		if (messages.size() >= 1) {
			model.addAttribute("message", messages);
			return "professorAdd";
		}*/

		Professor professor = new Professor(id, name, major);
		if (messages.size() >= 1) {
			model.addAttribute("message", messages);
			model.addAttribute("professor", professor);
			return "professorUpdate";
		}
		//ここまで

		professorRepository.save(professor);

		return "redirect:/professor";
	}

	//教授削除
	@PostMapping("/professor/{id}/delete")
	public String deleteProfessorf(
			@PathVariable("id") Integer id,
			Model model) {
		professorRepository.deleteById(id);
		return "redirect:/professor";
	}
}
