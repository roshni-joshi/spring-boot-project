package com.product.productwebservices.controller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.productwebservices.dto.CustomerDTO;
import com.product.productwebservices.dto.CustomerListDTO;
import com.product.productwebservices.exception.CustomerValidationException;
import com.product.productwebservices.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	private static final String PIPE = "|";
	private CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}
	
	@RequestMapping({"/", ""})
	public CustomerListDTO getAllCustomers()
	{
		return new CustomerListDTO(customerService.getAllCustomers());
	}
	
	@RequestMapping("/{id}")
	public CustomerDTO getCustomerById(@PathVariable("id") final String id)
	{
		return customerService.getCustomerById(Long.valueOf(id));
	}
	
	@PostMapping({"/",""})
	public CustomerDTO createCustomer(@Valid @RequestBody final CustomerDTO customerDTO, final BindingResult bindingResult)
	{
		StringBuilder stringBuilder = new StringBuilder();
		if(bindingResult != null && bindingResult.hasErrors())
		{
			bindingResult.getAllErrors().forEach(error -> {
				stringBuilder.append(PIPE);
				stringBuilder.append(error.getDefaultMessage());
			});
			
			String validationErrors = stringBuilder.toString();
			
			throw new CustomerValidationException(validationErrors);
		}
		return customerService.createCustomer(customerDTO);
	}
	
	@PutMapping("/{id}")
	public CustomerDTO updateCustomer(@PathVariable final String id, @RequestBody final CustomerDTO customerDTO)
	{
		CustomerDTO updated = null;
		
		if(customerDTO != null)
		{
			if(id != null)
			{
				customerDTO.setId(Long.valueOf(id));
			}
			updated = customerService.updateCustomer(customerDTO);
		}
		
		return updated;
	}
	
	@DeleteMapping("/{id}")
	public void deleteCustomer(@PathVariable("id") final String id)
	{
		if(id != null)
		{
			customerService.deleteCustomer(Long.valueOf(id));
			
		}
	}
	
}
