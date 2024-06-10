package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_details")
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "text_id")
	private Integer textId;

	@Column(name = "information_id")
	private Integer informationId;

	private Integer quantity;

	public OrderDetail() {
	}

	public OrderDetail(Integer textId, Integer informationId, Integer quantity) {
		this.textId = textId;
		this.informationId = informationId;
		this.quantity = quantity;
	}

	public Integer getId() {
		return id;
	}

	public Integer getTextId() {
		return textId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getInformationId() {
		return informationId;
	}

	public void setInformationId(Integer informationId) {
		this.informationId = informationId;
	}

}