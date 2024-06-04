package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {

	List<Manager> findByPassword(String password);

	//内部設計書CL012参照
}
