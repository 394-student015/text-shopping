package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Textbook;

public interface TextRepository extends JpaRepository<Textbook, Integer> {
	List<Textbook> findAll();

	List<Textbook> findByProfessorId(Integer professorId);

	List<Textbook> findByLessonId(Integer lessonId);

	List<Textbook> findStockById(Integer id);

<<<<<<< HEAD
	//List<Textbook> findById(Integer text.getTextId());
=======
>>>>>>> branch 'master' of git@github.com:394-student015/text-shopping.git
}
