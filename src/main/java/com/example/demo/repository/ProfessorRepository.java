package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

	List<Professor> findIdById(Integer professorId);

}
