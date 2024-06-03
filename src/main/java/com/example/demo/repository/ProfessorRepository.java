package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
	//内部設計書CL011参照
}
