package com.example.demo.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class AccountConfirm {

	private String name;
	private String email;
	private String tel;
	private String address;
	private String password;
	private Integer coupon;
	private String oldName;
	private String oldAddress;
	private String oldTel;
	private String oldEmail;
	private String oldPassword;

	public AccountConfirm(String name, String email, String tel, String address, String password, Integer coupon,
			String oldName, String oldAddress, String oldTel, String oldEmail, String oldPassword) {
		this.name = name;
		this.email = email;
		this.tel = tel;
		this.address = address;
		this.password = password;
		this.coupon = coupon;
		this.oldName = oldName;
		this.oldAddress = oldAddress;
		this.oldTel = oldTel;
		this.oldEmail = oldEmail;
		this.oldPassword = oldPassword;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public String getOldAddress() {
		return oldAddress;
	}

	public void setOldAddress(String oldAddress) {
		this.oldAddress = oldAddress;
	}

	public String getOldTel() {
		return oldTel;
	}

	public void setOldTel(String oldTel) {
		this.oldTel = oldTel;
	}

	public String getOldEmail() {
		return oldEmail;
	}

	public void setOldEmail(String oldEmail) {
		this.oldEmail = oldEmail;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public Integer getCoupon() {
		return coupon;
	}

	public void setCoupon(Integer coupon) {
		this.coupon = coupon;
	}

}
