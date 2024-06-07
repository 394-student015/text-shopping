package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Lesson;
import com.example.demo.repository.LessonRepository;

@Controller
public class LessonController {
	//内部設計書CL020参照
	@Autowired
	LessonRepository lessonRepository;

	//授業画面一覧表示
	@GetMapping("/lesson")
	public String lesson(Model model) {
		List<Lesson> lessonList = lessonRepository.findAll();
		model.addAttribute("lesson", lessonList);
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
		//List<Lesson> lessonList = lessonRepository.findByName(name);
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
		Lesson lesson = new Lesson(id, name);
		lessonRepository.save(lesson);
		return "redirect:/lesson";
	}

	//授業削除
	@PostMapping("/lesson/{id}/delete")
	public String deleteLesson(
			@PathVariable("id") Integer id,
			Model model) {
		lessonRepository.deleteById(id);
		return "redirect:/lesson";
	}
}
