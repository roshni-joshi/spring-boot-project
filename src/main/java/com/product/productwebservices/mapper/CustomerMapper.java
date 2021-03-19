package com.product.productwebservices.mapper;

import com.product.productwebservices.dto.CustomerDTO;
import com.product.productwebservices.model.Customer;

public interface CustomerMapper {

	Customer customerDTOToCustomer(final CustomerDTO customerDTO);
	
	CustomerDTO customerToCustomerDTO(final Customer customer);
}
