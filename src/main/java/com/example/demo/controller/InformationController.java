package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.repository.Information;
import com.example.demo.repository.InformationRepository;

@Controller

public class InformationController {
	@Autowired
	InformationRepository informationRepository;

	//内部設計書CL016参照
	//購入者側履歴表示
	@GetMapping("/information/{member_id}")
	public String information(
			@PathVariable("member_id") Integer id,
			Model model) {

		List<Information> informationList = informationRepository.findAll(id);
		model.addAttribute("information", informationList);

		return "menu";
	}
	//管理者側履歴表示

}
