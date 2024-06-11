package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Information;
import com.example.demo.entity.OrderDetail;
import com.example.demo.model.Member;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.InformationRepository;
import com.example.demo.repository.OrderDetailRepository;

@Controller

public class InformationController {
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	InformationRepository informationRepository;
	@Autowired
	BookRepository bookRepository;
	@Autowired
	OrderDetailRepository orderDetailRepository;
	@Autowired
	Member member;

	//内部設計書CL016参照
	//購入者側履歴表示
	@GetMapping("/information")
	public String information(
			Model model) {
		List<Information> informationOrder = informationRepository
				.findTitleAndTotalpriceAndPaymentAndReceivefindByMemberId(member.getId());
		//List<Information> information = informationOrder.get(0);
		//informationHistory.add(information);
		//List<Book> textbookList = new ArrayList();
		//for (Textbook text : cart.getTextbookList()) {

		//textbookList.add(bookRepository.findById(text.getId()).get());

		//}
		//for (InformationHistory info : informationHistory.getInformationList()) {

		//info.add(accountRepository.findNameAndEmailAndTelById(member.getId()));

		//model.addAttribute("informationList", informationList);

		//}
		return "menu";
	}

	//管理者側履歴表示
	@GetMapping("/orderHistory")
	public String orderHistory(
			Model model) {
		List<Information> informationList = informationRepository.findAll();
		model.addAttribute("informationList", informationList);

		//InformationHistory.setTitle(informationList.get(informationList));
		//一番前dateを追加
		//InformationHistory InformationHistory = new InformationHistory(name, email, tel,
		//title, totalprice, payment, receive);
		//.findByMemberIdAndTitleAndTotalpriceAndPaymentAndReceive();
		//List<Information> informationList = informationRepository.findMemberIdAndTitleAndTotalpriceAndPaymentAndReceive();
		//List<Account> accountList = accountRepository.findNameAndEmailAndTelById(memberId);

		return "informationHistory";
	}

	//管理者側履歴詳細表示
	@PostMapping("/orderHistory/detail")
	public String detail(
			@RequestParam(name = "informationId") Integer informatoinId,
			Model model) {

		List<OrderDetail> orderDetailList = orderDetailRepository.findAll();
		model.addAttribute("orderDetailList", orderDetailList);

		return "orderDetail";
	}

}
