package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

	List<Book> findByTitleContaining(String title);

	List<Book> findByProfessorContaining(String professor);

	List<Book> findByLectureContaining(String lecture);
}
