package com.product.productwebservices.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.product.productwebservices.dto.CustomerDTO;
import com.product.productwebservices.mapper.CustomerMapper;
import com.product.productwebservices.mapper.CustomerMapperImpl;
import com.product.productwebservices.model.Customer;
import com.product.productwebservices.repository.CustomerRepository;

class CustomerServiceJpaImplTest {
	
	private static final String XYZ = "xyz";
	private static final String ABC = "abc";
	private CustomerService customerService;
	private CustomerMapper customerMapper;
	
	@Mock
	private CustomerRepository customerRepository;
	
	private Customer cust1;
	private Customer cust2;
	
	@BeforeEach
	void setUp() throws Exception {
		
		MockitoAnnotations.openMocks(this);
		customerMapper = new CustomerMapperImpl();
		customerService = new CustomerServiceJpaImpl(customerMapper, customerRepository);
		
		loadCustomers();
	}
	
	private void loadCustomers()
	{
		cust1 = new Customer(1l, ABC, ABC, ABC, ABC);
		cust2 = new Customer(2l, XYZ, XYZ, XYZ, XYZ);
	}

	@Test
	void testGetAllCustomers() {
		when(customerRepository.findAll()).thenReturn(Arrays.asList(cust1, cust2));
		
		List<CustomerDTO> customerDTOs = customerService.getAllCustomers();
		assertEquals(2, customerDTOs.size());
	}

	@Test
	void testGetCustomerById() {
		
		when(customerRepository.findById(anyLong())).thenReturn(Optional.of(cust1));
		
		CustomerDTO customerDTO = customerService.getCustomerById(1l);
		
		assertNotNull(customerDTO);
		assertEquals(1l, customerDTO.getId());
		assertEquals(ABC, customerDTO.getFirstName());
		assertEquals(ABC, customerDTO.getLastName());
		assertEquals(ABC, customerDTO.getEmail());
		assertEquals(ABC, customerDTO.getCity());
	}

	@Test
	void testCreateCustomer() {
		
		when(customerRepository.save(any())).thenReturn(cust1);
		
		CustomerDTO customerDTO = customerService.createCustomer(customerMapper.customerToCustomerDTO(cust1));
		
		assertNotNull(customerDTO);
		assertEquals(1l, customerDTO.getId());
		assertEquals(ABC, customerDTO.getFirstName());
		assertEquals(ABC, customerDTO.getLastName());
		assertEquals(ABC, customerDTO.getEmail());
		assertEquals(ABC, customerDTO.getCity());
	}

	@Test
	void testUpdateCustomer() {
	
		cust1.setFirstName("demo");
		when(customerRepository.save(any())).thenReturn(cust1);
		
		CustomerDTO customerDTO = customerService.updateCustomer(customerMapper.customerToCustomerDTO(cust1));
		
		assertNotNull(customerDTO);
		assertEquals(1l, customerDTO.getId());
		assertEquals("demo", customerDTO.getFirstName());
		assertEquals(ABC, customerDTO.getLastName());
		assertEquals(ABC, customerDTO.getEmail());
		assertEquals(ABC, customerDTO.getCity());
	}

	@Test
	void testDeleteCustomer() {
		customerService.deleteCustomer(1l);
		verify(customerRepository, times(1)).deleteById(anyLong());
	}

}
