package com.product.productwebservices.service;

import java.util.List;

import com.product.productwebservices.dto.CustomerDTO;

public interface CustomerService {
	
	List<CustomerDTO> getAllCustomers();
	
	CustomerDTO getCustomerById(final Long id);
	
	CustomerDTO createCustomer(final CustomerDTO customerDTO);
	
	CustomerDTO updateCustomer(final CustomerDTO customerDTO);
	
	void deleteCustomer(final Long id);
}
