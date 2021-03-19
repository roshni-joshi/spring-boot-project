package com.product.productwebservices.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.product.productwebservices.dto.CustomerDTO;
import com.product.productwebservices.mapper.CustomerMapper;
import com.product.productwebservices.model.Customer;

@Service
@Profile("map")
public class CustomerServiceMapImpl implements CustomerService {
	
	Map<Long, Customer> customerMap = new HashMap<Long, Customer>();
	
	private CustomerMapper customerMapper;
	
	public CustomerServiceMapImpl(CustomerMapper customerMapper) {
		this.customerMapper = customerMapper;
	}

	@Override
	public List<CustomerDTO> getAllCustomers() {
		
		System.out.println("Customer Map : getAllCustomers");
		
		List<CustomerDTO> customerDTOList = new ArrayList<CustomerDTO>();
		
		if(customerMap != null && customerMap.size() > 0)
		{
			customerDTOList = customerMap.values().stream().map(customerMapper::customerToCustomerDTO).collect(Collectors.toList());
		}
		
		return customerDTOList;
	}

	@Override
	public CustomerDTO getCustomerById(Long id) {
		
		System.out.println("Customer Map : GetCustomerById " + id);
		
		CustomerDTO customerDTO = null;
		
		if(id != null)
		{
			if(customerMap != null && customerMap.containsKey(id))
			{
				Customer customer = customerMap.get(id);
				if(customer != null)
				{
					customerDTO = customerMapper.customerToCustomerDTO(customer);
				}
			}
		}
		
		return customerDTO;
	}

	@Override
	public CustomerDTO createCustomer(CustomerDTO customerDTO) {
		
		System.out.println("Customer Map : Create Customer");
		
		CustomerDTO savedDTO = null;
		
		if(customerDTO != null)
		{
			if(customerDTO.getId() == null)
			{
				customerDTO.setId(getNextId());
			}
			
			Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
			if(customer != null)
			{
				customerMap.put(customer.getId(), customer);
				savedDTO = customerMapper.customerToCustomerDTO(customer);
			}
		}
		
		return savedDTO;
	}

	@Override
	public CustomerDTO updateCustomer(CustomerDTO customerDTO) {

		System.out.println("Customer Map : update customer");
		return createCustomer(customerDTO);
	}

	@Override
	public void deleteCustomer(Long id) {
		
		System.out.println("Customer Map : delete customer by id " + id);
		
		if(id != null && customerMap.containsKey(id))
		{
			customerMap.remove(id);
		}
	}
	
	private Long getNextId()
	{
		Long id = null;
		
		if(customerMap != null)
		{
			if(customerMap.size() == 0)
			{
				id = 1l;
			}
			else
			{
				id = Collections.max(customerMap.keySet()) + 1;
			}
		}
		return id;
	}

}
