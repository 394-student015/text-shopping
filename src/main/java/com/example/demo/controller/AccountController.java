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
import com.example.demo.model.AccountConfirm;
import com.example.demo.model.Member;
import com.example.demo.repository.AccountRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AccountController {
	@Autowired
	HttpSession session;
	@Autowired
	Member member;
	@Autowired
	AccountRepository accountRepository;

	//会員登録画面表示
	@GetMapping("/account")
	public String create(Model model) {
		Account customer = new Account();
		model.addAttribute("customer", customer);
		return "create";
	}

	//会員登録処理
	@PostMapping("/account")
	public String store(
			@RequestParam(name = "name") String name,
			@RequestParam(name = "email") String email,
			@RequestParam(name = "tel") String tel,
			@RequestParam(name = "address") String address,
			@RequestParam(name = "password") String password,
			@RequestParam(name = "coupon", defaultValue = "0") Integer coupon,
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
		Account registration = new Account(name, email, tel, address, password, coupon);
		model.addAttribute("registration", registration);
		accountRepository.save(registration);

		return "createConfirm";

	}

	//購入者のログイン画面表示
	@GetMapping("/")
	public String index() {
		//session.invalidate();
		return "login";
	}

	//購入者のログイン処理
	@PostMapping("/login")
	public String login(
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "password", defaultValue = "") String password,
			Model model) {
		List<Account> accountInfo = accountRepository.findByEmailAndPassword(email, password);
		if (accountInfo == null || accountInfo.size() == 0) {
			model.addAttribute("message", "正しく入力してください");
			return "login";
		} else {
			Account account = accountInfo.get(0);
			member.setId(account.getId());
			//member.setName(account.getName());
			return "redirect:/shopMenu";
		}
	}

	//会員削除画面
	@GetMapping("/memberUpdate/confirm")
	public String memberUpdateConfirm() {
		return "memberDelate";

	}

	//会員更新確認画面
	@GetMapping("/memberUpdate/confirm")
	public String memberUpdateConfirm(
			@RequestParam(name = "member_name", defaultValue = "") String name,
			@RequestParam(name = "member_address", defaultValue = "") String address,
			@RequestParam(name = "member_tel", defaultValue = "") String tel,
			@RequestParam(name = "member_email", defaultValue = "") String email,
			@RequestParam(name = "member_password", defaultValue = "") String password,
			@RequestParam(name = "coupon", defaultValue = "") Integer coupon,
			@RequestParam(name = "old_member_name", defaultValue = "") String oldName,
			@RequestParam(name = "old_member_address", defaultValue = "") String oldAddress,
			@RequestParam(name = "old_member_tel", defaultValue = "") String oldTel,
			@RequestParam(name = "old_member_email", defaultValue = "") String oldEmail,
			@RequestParam(name = "old_member_password", defaultValue = "") String oldPassword,
			Model model) {

		AccountConfirm accountUpdateConfirm = new AccountConfirm(name, email, tel, address, password,
				coupon, oldName, oldAddress, oldTel, oldEmail, oldPassword);
		if (name.length() == 0) {
			accountUpdateConfirm.setName(oldName);
		}
		if (email.length() == 0) {
			accountUpdateConfirm.setEmail(oldEmail);
		}
		if (tel.length() == 0) {
			accountUpdateConfirm.setTel(oldTel);
		}
		if (address.length() == 0) {
			accountUpdateConfirm.setAddress(oldAddress);
		}
		if (password.length() == 0) {
			accountUpdateConfirm.setPassword(oldPassword);
		}
		model.addAttribute("accountUpdateConfirm", accountUpdateConfirm);
		return "memberUpdateConfirm";

	}

	@PostMapping("/memberUpdate/confirm")
	public String memberUpdateConfirmFinish(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "tel", defaultValue = "") String tel,
			@RequestParam(name = "address", defaultValue = "") String address,
			@RequestParam(name = "password", defaultValue = "") String password,
			@RequestParam(name = "coupon", defaultValue = "") Integer coupon,
			@RequestParam(name = "old_member_name", defaultValue = "") String oldName,
			@RequestParam(name = "old_member_address", defaultValue = "") String oldAddress,
			@RequestParam(name = "old_member_tel", defaultValue = "") String oldTel,
			@RequestParam(name = "old_member_email", defaultValue = "") String oldEmail,
			@RequestParam(name = "old_member_password", defaultValue = "") String oldPassword,
			Model model) {

		Account accountUpdate = new Account(name, email, tel, address, password, coupon);
		accountUpdate.setId(member.getId());
		accountRepository.save(accountUpdate);
		return "redirect:/shopMenu";

	}

	//会員更新画面
	@GetMapping("/memberUpdate")

	public String memberUpdate(
			Model model) {
		Integer accountUpdate = member.getId();
		Account account = accountRepository.findById(accountUpdate).get();
		model.addAttribute("account", account);
		return "memberUpdate";

	}

	//会員一覧表示
	@GetMapping("/memberInfo")
	public String memberInfo(
			Model model) {

		Integer accountId = member.getId();
		List<Account> accountList = accountRepository.findAllById(accountId);
		model.addAttribute("accountList", accountList);
		return "memberInfo";

	}
}
