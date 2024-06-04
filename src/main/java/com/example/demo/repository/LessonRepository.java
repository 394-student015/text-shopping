package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
	//内部設計書CL010参照
}
