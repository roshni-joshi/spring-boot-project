package com.product.productwebservices.dto;

import java.util.List;

public class CustomerListDTO {
	
	private List<CustomerDTO> customers;

	public CustomerListDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public CustomerListDTO(List<CustomerDTO> customers) {
		super();
		this.customers = customers;
	}

	public List<CustomerDTO> getCustomers() {
		return customers;
	}

	public void setCustomers(List<CustomerDTO> customers) {
		this.customers = customers;
	}
}
