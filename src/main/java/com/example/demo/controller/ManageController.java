package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Manager;
import com.example.demo.repository.ManagerRepository;

@Controller
public class ManageController {
	@Autowired
	ManagerRepository managerRepository;

	//管理者の初期画面表示
	@GetMapping("/manager")
	public String manager() {
		return "manager";
	}

	//管理者のログイン画面表示
	@GetMapping("/signin")
	public String signin() {
		return "signin";
	}

	//管理者のログイン処理
	@PostMapping("/entry")
	public String login(
			@RequestParam("password") String password,
			Model model) {
		List<Manager> managerList = managerRepository.findByPassword(password);
		if (managerList == null || managerList.size() == 0) {
			model.addAttribute("message", "正しく入力してください");
			return "signin";
		}
		//Account account = accountList.get(0);
		return "manager";
	}

}
