package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

}
