package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.model.Account;

@Controller
public class AccountController {

	@Autowired
	Account account;

}
