package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Account;

import jakarta.servlet.http.HttpSession;

@Controller
public class AccountController {

	@Autowired
	Account account;
	@Autowired
	HttpSession session;

	@GetMapping("/")
	public String index() {
		session.invalidate();
		return "login";
	}

	@PostMapping("/login")
	public String login() {

		return "menu";
	}

}
