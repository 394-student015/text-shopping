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

import com.example.demo.entity.Lesson;
import com.example.demo.entity.Textbook;
import com.example.demo.repository.LessonRepository;
import com.example.demo.repository.TextRepository;

@Controller
public class LessonController {
	@Autowired
	LessonRepository lessonRepository;

	@Autowired
	TextRepository textRepository;

	//授業画面一覧表示
	@GetMapping("/lesson")
	public String lesson(Model model) {
		List<Lesson> lessonList = lessonRepository.findAll();
		model.addAttribute("lessons", lessonList);
		return "lesson";
	}

	//新規追加画面
	@GetMapping("/lesson/add")
	public String lessonAdd(Model model) {
		return "LessonAdd";
	}

	//授業新規追加
	@PostMapping("/lesson/add")
	public String addLesson(
			@RequestParam(value = "name", defaultValue = "") String name,
			Model model) {

		//エラーメッセージ表示、ここから
		if (name == null || name.length() == 0) {
			model.addAttribute("message", "授業名は必須です");
			return "lessonAdd";
		}
		//ここまで

		Lesson lesson = new Lesson(name);
		lessonRepository.save(lesson);
		return "redirect:/lesson";
	}

	//授業更新画面表示
	@GetMapping("/lesson/{id}/edit")
	public String editLesson(
			@PathVariable("id") Integer id,
			Model model) {
		Lesson lesson = lessonRepository.findById(id).get();
		model.addAttribute("lesson", lesson);
		return "lessonUpdate";
	}

	//授業更新
	@PostMapping("/lesson/{id}/edit")
	public String updateLesson(
			@PathVariable("id") Integer id,
			@RequestParam(value = "name", defaultValue = "") String name,
			Model model) {

		//エラーメッセージ表示、ここから

		if (name == null || name.length() == 0) {
			model.addAttribute("message", "授業名は必須です");
			return "lessonAdd";
		}
		//ここまで

		Lesson lesson = new Lesson(id, name);
		lessonRepository.save(lesson);
		return "redirect:/lesson";
	}

	//授業削除
	@PostMapping("/lesson/{id}/delete")
	public String deleteLesson(
			@PathVariable("id") Integer id,
			Model model) {

		//エラーメッセージ表示
		List<String> messages = new ArrayList<String>();

		List<Textbook> textbookList = textRepository.findByProfessorId(id);

		if (textbookList != null && textbookList.size() > 0) {
			messages.add("削除を試みた授業情報が、追加済みの教科書情報により参照されています。");
			messages.add("まずは教科書情報の削除をお試しください。");
		}

		if (messages.size() > 0) {
			List<Lesson> lessonList = lessonRepository.findAll();
			model.addAttribute("lessons", lessonList);

			model.addAttribute("message", messages);
			return "lesson";
		}

		lessonRepository.deleteById(id);
		return "redirect:/lesson";
	}
}
