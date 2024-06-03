package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.model.Cart;

@Controller
public class CartController {
	//内部設計書CL015参照
	@Autowired
	Cart cart;

}
