package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AccountController {

	@Autowired
	AccountRepository accountRepository;
	@Autowired
	HttpSession session;

	//会員登録画面表示
	@GetMapping("/account")
	public String create(Model model) {
		Account customer = new Account();
		model.addAttribute("customer", customer);
		return "";
	}

	@PostMapping("/account")
	public String store(
			@RequestParam(name = "name") String name,
			@RequestParam(name = "email") String email,
			@RequestParam(name = "tel") String tel,
			@RequestParam(name = "address") String address,
			@RequestParam(name = "password") String password,
			Model model) {
		List<String> messages = new ArrayList<String>();
		if (name.isEmpty()) {
			messages.add("名前は必須です");
		}
		if (email.isEmpty()) {
			messages.add("メールアドレスは必須です");
		}
		if (tel.isEmpty()) {
			messages.add("電話番号は必須です");
		}
		if (address.isEmpty()) {
			messages.add("住所は必須です");
		}
		if (password.isEmpty()) {
			messages.add("パスワードは必須です");
		}

		Account registrationList = new Account(name, email, tel, address, password);
		model.addAttribute("registration", registrationList);
		if (messages.size() >= 1) {
			model.addAttribute("messages", messages);

			return "create";
		}
		Account registration = new Account(name, email, tel, address, password);
		accountRepository.save(registration);
		return "createConfirm";

	}

	@GetMapping("/accont/confirm")
	public String createConfirm(Model model) {
		List<Account> accountConfirm = accountRepository.findAll();
		model.addAttribute("accountConfirm", accountConfirm);
		return "createConfirm";
	}

	//購入者のログイン画面表示
	@GetMapping("/")
	public String index() {
		session.invalidate();
		return "login";
	}

	//購入者のログイン処理
	@PostMapping("/login")
	public String login(
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			Model model) {
		List<Account> accountList = accountRepository.findByEmailAndPassword(email, password);
		if (accountList == null || accountList.size() == 0) {
			model.addAttribute("message", "正しく入力してください");
			return "login";
		}
		//Account account = accountList.get(0);
		return "redirect:/menu";
	}
}
