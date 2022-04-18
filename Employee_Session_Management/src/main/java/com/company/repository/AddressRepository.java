package com.company.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.company.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{
	
	//deleting the address of a particular employee while updating
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM Address WHERE emp_id = :empId", nativeQuery = true)
	public void deleteAddress(@Param("empId") int emp_id);

}
