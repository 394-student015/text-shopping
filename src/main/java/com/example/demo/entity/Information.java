package com.example.demo.entity;

import java.time.LocalDate;
import java.util.List;

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

	private List<Account> informationList;
	private String title;
	private Integer stock;
	private String className;
	private String proName;
	private String major;
	private Integer totalPrice;

	//コンストラクタ
	public Information(List<Account> informationList, Integer textId, String title, Integer stock, String className,
			String proName, String major, Integer totalPrice, Integer receive) {
		this.informationList = informationList;
		this.textId = textId;
		this.title = title;
		this.stock = stock;
		this.className = className;
		this.proName = proName;
		this.major = major;
		this.totalPrice = totalPrice;
		this.receive = receive;

	}

	public Information(Integer id, Integer memberId, Integer textId, LocalDate date, Integer totalprice,
			Integer payment,
			Integer receive) {
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

	public List<Account> getInformationList() {
		return informationList;
	}

	public String getTitle() {
		return title;
	}

	public Integer getStock() {
		return stock;
	}

	public String getClassName() {
		return className;
	}

	public String getProName() {
		return proName;
	}

	public String getMajor() {
		return major;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

}
