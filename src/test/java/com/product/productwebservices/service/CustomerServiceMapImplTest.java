package com.product.productwebservices.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.product.productwebservices.dto.CustomerDTO;
import com.product.productwebservices.mapper.CustomerMapperImpl;
import com.product.productwebservices.model.Customer;

class CustomerServiceMapImplTest {
	
	private CustomerService customerService;
	
	private static final String CITY = "Ahmedabad";
	private static final String EMAIL = "abc@gmail.com";
	private static final String LASTNAME = "Joshi";
	private static final String FIRSTNAME = "Roshni";
	private static final long ID = 1l;

	@BeforeEach
	void setUp() throws Exception {
		customerService = new CustomerServiceMapImpl(new CustomerMapperImpl());
		
		CustomerDTO customerDTO = new CustomerDTO(ID, FIRSTNAME, LASTNAME, EMAIL, CITY);
		customerService.createCustomer(customerDTO);
	}

	@Test
	void testGetAllCustomers() {
		List<CustomerDTO> customerDTOs = customerService.getAllCustomers();
		
		assertEquals(1, customerDTOs.size());
		assertEquals(FIRSTNAME, customerDTOs.get(0).getFirstName());
		assertEquals(LASTNAME, customerDTOs.get(0).getLastName());
		assertEquals(ID, customerDTOs.get(0).getId());
		assertEquals(EMAIL, customerDTOs.get(0).getEmail());
		assertEquals(CITY, customerDTOs.get(0).getCity());
	}

	@Test
	void testGetCustomerById() {
		
		CustomerDTO customerDTO = customerService.getCustomerById(ID);
		
		assertNotNull(customerDTO);
		assertEquals(FIRSTNAME, customerDTO.getFirstName());
		assertEquals(LASTNAME, customerDTO.getLastName());
		assertEquals(ID, customerDTO.getId());
		assertEquals(EMAIL, customerDTO.getEmail());
		assertEquals(CITY, customerDTO.getCity());
	}
	
	@Test
	void testGetCustomerByNonExistenceId() {
		
		CustomerDTO customerDTO = customerService.getCustomerById(5l);
		assertNull(customerDTO);
	}


	@Test
	void testCreateCustomer() {
		
		CustomerDTO customerDTOToSave = new CustomerDTO(2l, FIRSTNAME, LASTNAME, EMAIL, CITY);
		CustomerDTO customerDTO = customerService.createCustomer(customerDTOToSave);
		
		assertNotNull(customerDTO);
		assertEquals(FIRSTNAME, customerDTO.getFirstName());
		assertEquals(LASTNAME, customerDTO.getLastName());
		assertEquals(2l, customerDTO.getId());
		assertEquals(EMAIL, customerDTO.getEmail());
		assertEquals(CITY, customerDTO.getCity());
	}
	
	@Test
	void testCreateCustomerWithoutId() {
		
		CustomerDTO customerDTOToSave = new CustomerDTO(null, FIRSTNAME, LASTNAME, EMAIL, CITY);
		CustomerDTO customerDTO = customerService.createCustomer(customerDTOToSave);
		
		assertNotNull(customerDTO);
		assertEquals(FIRSTNAME, customerDTO.getFirstName());
		assertEquals(LASTNAME, customerDTO.getLastName());
		assertEquals(2l, customerDTO.getId());
		assertEquals(EMAIL, customerDTO.getEmail());
		assertEquals(CITY, customerDTO.getCity());
	}
	
	@Test
	void testCreateNullCustomer() {
	
		CustomerDTO customerDTO = customerService.createCustomer(null);	
		assertNull(customerDTO);
	}


	@Test
	void testUpdateCustomer() {
		CustomerDTO customerDTOToUpdate = new CustomerDTO(ID, "new name", LASTNAME, EMAIL, CITY);
		CustomerDTO customerDTO = customerService.updateCustomer(customerDTOToUpdate);
		
		assertNotNull(customerDTO);
		assertEquals("new name", customerDTO.getFirstName());
		assertEquals(LASTNAME, customerDTO.getLastName());
		assertEquals(ID, customerDTO.getId());
		assertEquals(EMAIL, customerDTO.getEmail());
		assertEquals(CITY, customerDTO.getCity());
		
	}

	@Test
	void testDeleteCustomer() {
		assertNotNull(customerService.getCustomerById(ID));
		customerService.deleteCustomer(ID);
		assertNull(customerService.getCustomerById(ID));
	}

}
