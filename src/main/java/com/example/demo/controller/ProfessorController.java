package com.example.demo.controller;

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

	//教授新規追加
	@PostMapping("/professor/add")
	public String addProfessor(
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "major", defaultValue = "") String major,
			Model model) {
		Professor professor = new Professor(name, major);
		professorRepository.save(professor);
		return "professorAdd";
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
		Professor professor = new Professor(id, name, major);
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
