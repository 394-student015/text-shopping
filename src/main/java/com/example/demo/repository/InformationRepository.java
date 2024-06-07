package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Information;

public interface InformationRepository extends JpaRepository<Information, Integer> {

	//List<Information> findAll(Integer id);
	List<Information> findByMemberId(Integer memberId);

	List<Information> findTitleAndTotalpriceAndPaymentAndReceivefindByMemberId(Integer id);

	//List<InformationHistory> findTitleAndTotalpriceAndPaymentAndReceivefindByMemberId(Integer id);
}
