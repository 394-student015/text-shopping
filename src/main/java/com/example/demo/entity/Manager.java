package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "management")
//内部設計書CL06参照
public class Manager {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
}
