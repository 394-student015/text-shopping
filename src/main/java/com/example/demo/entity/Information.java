package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "information")
//内部設計書CL03参照
public class Information {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "member_id")
	private Integer memberId;

	@Column(name = "text_id")
	private Integer textId;

	private LocalDate date;

	private Integer totalprice;
	private Integer payment;
	private Integer receive;

	//コンストラクタ
	public Information() {
	}

	public Information(Integer id, Integer memberId, Integer textId, Integer totalprice,
			Integer payment, Integer receive) {
		this.id = id;
		this.memberId = memberId;
		this.textId = textId;
		this.totalprice = totalprice;
		this.payment = payment;
		this.receive = receive;
	}

	//textIdありバージョン
	public Information(Integer memberId, Integer textId, LocalDate date, Integer totalprice,
			Integer payment, Integer receive) {
		this.memberId = memberId;
		this.textId = textId;
		this.date = date;
		this.totalprice = totalprice;
		this.payment = payment;
		this.receive = receive;
	}

	//textIdなしバージョン
	public Information(Integer memberId, LocalDate date, Integer totalprice,
			Integer payment, Integer receive) {
		this.memberId = memberId;
		this.date = date;
		this.totalprice = totalprice;
		this.payment = payment;
		this.receive = receive;
	}

	public Information(Integer id, Integer memberId, Integer textId, LocalDate date, Integer totalprice,
			Integer payment, Integer receive) {
		this.id = id;
		this.memberId = memberId;
		this.textId = textId;
		this.date = date;
		this.totalprice = totalprice;
		this.payment = payment;
		this.receive = receive;
	}

	//ゲッターとセッター
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getTextId() {
		return textId;
	}

	public void setTextId(Integer textId) {
		this.textId = textId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(Integer totalprice) {
		this.totalprice = totalprice;
	}

	public Integer getPayment() {
		return payment;
	}

	public void setPayment(Integer payment) {
		this.payment = payment;
	}

	public Integer getReceive() {
		return receive;
	}

	public void setReceive(Integer receive) {
		this.receive = receive;
	}

}
