package com.product.productwebservices.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.product.productwebservices.dto.CustomerDTO;
import com.product.productwebservices.model.Customer;

class CustomerMapperImplTest {

	private static final String CITY = "Ahmedabad";
	private static final String EMAIL = "abc@gmail.com";
	private static final String LASTNAME = "Joshi";
	private static final String FIRSTNAME = "Roshni";
	private static final long ID = 1l;

	private CustomerMapper customerMapper;

	@BeforeEach
	void setUp() throws Exception {
		customerMapper = new CustomerMapperImpl();
	}

	@Test
	void testCustomerDTOToCustomer() {
		
		CustomerDTO customerDTO = new CustomerDTO(ID, FIRSTNAME, LASTNAME, EMAIL, CITY);
		Customer customer = customerMapper.customerDTOToCustomer(customerDTO);

		assertNotNull(customer);
		assertEquals(FIRSTNAME, customer.getFirstName());
		assertEquals(LASTNAME, customer.getLastName());
		assertEquals(ID, customer.getId());
		assertEquals(EMAIL, customer.getEmail());
		assertEquals(CITY, customer.getCity());
	}

	@Test
	void testCustomerToCustomerDTO() {
		
		Customer customer = new Customer(ID, FIRSTNAME, LASTNAME, EMAIL, CITY);
		CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

		assertNotNull(customer);
		assertEquals(FIRSTNAME, customerDTO.getFirstName());
		assertEquals(LASTNAME, customerDTO.getLastName());
		assertEquals(ID, customerDTO.getId());
		assertEquals(EMAIL, customerDTO.getEmail());
		assertEquals(CITY, customerDTO.getCity());
	}
	
	@Test
	void testNullCustomerDTO() {
		
		Customer customer = customerMapper.customerDTOToCustomer(null);
		assertNull(customer);
	}
	
	@Test
	void testNullCustomer() {
		
		CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(null);
		assertNull(customerDTO);
	}
	
	@Test
	void testEmptyCustomerDTO() {
		
		CustomerDTO customerDTO = new CustomerDTO();
		Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
		
		assertNotNull(customer);
		assertNull(customer.getId());
		assertNull(customer.getFirstName());
		assertNull(customer.getLastName());
		assertNull(customer.getEmail());
		assertNull(customer.getCity());
	}
	
	@Test
	void testEmptyCustomer() {
		
		Customer customer = new Customer();
		CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
		
		assertNotNull(customerDTO);
		assertNull(customerDTO.getId());
		assertNull(customerDTO.getFirstName());
		assertNull(customerDTO.getLastName());
		assertNull(customerDTO.getEmail());
		assertNull(customerDTO.getCity());
	}
}
