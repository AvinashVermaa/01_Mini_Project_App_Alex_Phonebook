package com.avinash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.avinash.entities.ContactInfo;

import jakarta.transaction.Transactional;

@Repository
public interface ContactRepository extends JpaRepository<ContactInfo, Integer> {

	@Modifying
	@Transactional
	@Query("update ContactInfo set activeStatus=:status where sno=:sno")
	public void setActiveStatus(String status,Integer sno);
}
