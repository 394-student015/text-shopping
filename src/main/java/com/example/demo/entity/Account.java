package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "member")
//内部設計書CL02参照
public class Account {
	//フィールド
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String address;
	private String tel;
	private String email;
	private String password;
	private Integer coupon;

	//コンストラクタ
	public Account(String name, String email, String tel, String address, String password) {
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.email = email;
		this.password = password;
	}

	public Account(String name, String email, String tel, String address, String password, Integer coupon) {
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.email = email;
		this.password = password;
		this.coupon = coupon;
	}

	public Account(String name, String email, String tel, String address, String password, String oldName,
			String oldEmail, String oldTel, String oldAddress, String oldPassword) {
		this.name = name;
		this.email = email;
		this.tel = tel;
		this.address = address;
		this.password = password;
	}

	public Account(Integer id, String name, String email, String tel, String address, String password, Integer coupon) {
		this(name, email, tel, address, password, coupon);
		this.id = id;
	}

	public Account() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public Account(String name, String email, String tel, String address, String password, Integer coupon,
			String oldName, String oldAddress, String oldTel, String oldEmail, String oldPassword) {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	//ゲッター
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getTel() {
		return tel;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public Integer getCoupon() {
		return coupon;
	}

	//セッター
	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCoupon(Integer coupon) {
		this.coupon = coupon;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
