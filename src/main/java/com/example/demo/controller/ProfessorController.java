package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.repository.ProfessorRepository;

@Controller
public class ProfessorController {
	//内部設計書CL019参照
	@Autowired
	ProfessorRepository professorRepository;
	
@GetMapping("/professor")

}
