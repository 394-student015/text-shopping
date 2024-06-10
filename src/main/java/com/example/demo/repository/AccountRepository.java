package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

	List<Account> findByEmailAndPassword(String email, String password);

	//内部設計書CL08参照

	//List<Account> findByNameAndEmailAndTelAndAddressAndPassword(String name, String email, String tel, String address,
	//String password);

	List<Account> findByName(String name);
	//List<Account> findByIdAndName(Integer id, String name);

	List<Account> findAllById(Integer id);

	Account findCouponById(Integer id);

	//List<Account> findByNameAndEmailAndTelById(String name, String email, String tel);

	//InformationHistory findNameAndEmailAndTelById(Integer id);

	//List<Account> findCouponById(Integer accountId);

	//List<Account> findById(String coupon);

}
