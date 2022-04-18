package com.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.repository.AddressRepository;

@Service("addressService")
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	//Deleting all the address of a particular employee while updating
	public void deleteAddress(int empId) {
		addressRepository.deleteAddress(empId);
	}

}
