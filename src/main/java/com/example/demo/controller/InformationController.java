package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.Information;
import com.example.demo.repository.InformationRepository;

@Controller

public class InformationController {
	@Autowired
	InformationRepository informationRepository;

	//内部設計書CL016参照
	@GetMapping("/information/{member_id}")
	public String information(
			@RequestParam(name = "info_Id", defaultValue = "") Integer info_Id,
			Model model) {

		List<Information> informationList = informationRepository.findByInfomationId(info_Id);
		model.addAttribute("informationId");

		return "menu";
	}

}
