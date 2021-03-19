package com.product.productwebservices.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.productwebservices.dto.CustomerDTO;
import com.product.productwebservices.service.CustomerService;

class CustomerControllerTest {
	
	private static final String EMAIL = "abc@gmail.com";
	private static final String XYZ = "xyz";
	private static final String ABC = "abc";
	private static final String BASE_URL = "/api/customers";
	
	private CustomerController customerController;
	
	@Mock
	private CustomerService customerService;
	
	private MockMvc mockMvc;
	
	private CustomerDTO cust1;
	private CustomerDTO cust2;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		
		customerController = new CustomerController(customerService);
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
		loadCustomers();
	}

	private void loadCustomers()
	{
		cust1 = new CustomerDTO(1l, ABC, ABC, EMAIL, ABC);
		cust2 = new CustomerDTO(2l, XYZ, XYZ, EMAIL, XYZ);
	}

	@Test
	void testGetAllCustomers() throws Exception {
		
		when(customerService.getAllCustomers()).thenReturn(Arrays.asList(cust1, cust2));
		
		mockMvc.perform(get(BASE_URL).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.customers", Matchers.hasSize(2)));
	}

	@Test
	void testGetCustomerById() throws Exception {
		
		when(customerService.getCustomerById(anyLong())).thenReturn(cust1);
		
		mockMvc.perform(get(BASE_URL + "/1").accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id", Matchers.equalTo(1)))
		.andExpect(jsonPath("$.firstName", Matchers.equalTo(ABC)))
		.andExpect(jsonPath("$.lastName", Matchers.equalTo(ABC)))
		.andExpect(jsonPath("$.email", Matchers.equalTo(EMAIL)))
		.andExpect(jsonPath("$.city", Matchers.equalTo(ABC)));
	}

	@Test
	void testCreateCustomer() throws Exception {
		
		CustomerDTO customerDTO = new CustomerDTO(3l, ABC, ABC, EMAIL, ABC);
		
		when(customerService.createCustomer(ArgumentMatchers.any())).thenReturn(customerDTO);
		
		mockMvc.perform(post(BASE_URL).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString(customerDTO)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id", Matchers.equalTo(3)))
		.andExpect(jsonPath("$.firstName", Matchers.equalTo(ABC)))
		.andExpect(jsonPath("$.lastName", Matchers.equalTo(ABC)))
		.andExpect(jsonPath("$.email", Matchers.equalTo(EMAIL)))
		.andExpect(jsonPath("$.city", Matchers.equalTo(ABC)));
	}
	
	private String jsonString(Object object)
	{
		String jsonString = null;
		
		if(object != null)
		{
			try 
			{
				jsonString = new ObjectMapper().writeValueAsString(object);
			}
			catch(Exception e) {}
		}
		
		return jsonString;
	}

	@Test
	void testUpdateCustomer() throws Exception {
		cust1.setFirstName("new name");
		when(customerService.updateCustomer(any())).thenReturn(cust1);
		
		mockMvc.perform(put(BASE_URL + "/1").accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString(cust1)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id", Matchers.equalTo(1)))
		.andExpect(jsonPath("$.firstName", Matchers.equalTo("new name")))
		.andExpect(jsonPath("$.lastName", Matchers.equalTo(ABC)))
		.andExpect(jsonPath("$.email", Matchers.equalTo(EMAIL)))
		.andExpect(jsonPath("$.city", Matchers.equalTo(ABC)));
		
	}

	@Test
	void testDeleteCustomer() throws Exception {
		
		mockMvc.perform(delete(BASE_URL + "/12"));
		verify(customerService, times(1)).deleteCustomer(12l);
	}

}
