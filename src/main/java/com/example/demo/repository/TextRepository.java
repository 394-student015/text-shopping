package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Textbook;

public interface TextRepository extends JpaRepository<Textbook, Integer> {

	List<Textbook> findAll();
	//内部設計書CL07参照

}
