package com.product.productwebservices.bootstrap;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.product.productwebservices.dto.CustomerDTO;
import com.product.productwebservices.dto.ProductDTO;
import com.product.productwebservices.service.CustomerService;
import com.product.productwebservices.service.ProductService;

@Component
public class ProductDataLoader implements CommandLineRunner {

	private ProductService productService;
	
	private CustomerService customerService;

	public ProductDataLoader(ProductService productService, CustomerService customerService) {
		super();
		this.productService = productService;
		this.customerService = customerService;
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("in data loader");
		
		/*List<ProductDTO> products = productService.getAllProducts();
		
		if(products != null && products.size()==0)
		{
			loadProductData();
		}
		
		List<CustomerDTO> customers = customerService.getAllCustomers();
		if(customers != null && customers.size()==0)
		{
			loadCustomerData();
		}*/
		
		loadAggregateData();
	}
	
	public void loadAggregateData() {
		CustomerDTO dhruvi = new CustomerDTO(1l, "Dhruvi", "Joshi", "dd@gmail.com", "Ahmedabad");
		dhruvi = customerService.createCustomer(dhruvi);
		
		ProductDTO laptop = new ProductDTO();
		laptop.setId(1l);
		laptop.setName("laptop");
		laptop.setDescription("laptop");
		laptop.setPrice(new BigDecimal(1000));
		laptop.setCustomer(dhruvi);
		productService.createProduct(laptop);
		
		
		ProductDTO tablet = new ProductDTO();
		tablet.setId(2l);
		tablet.setName("tablet");
		tablet.setDescription("tablet");
		tablet.setPrice(new BigDecimal(500));
		tablet.setCustomer(dhruvi);
		productService.createProduct(tablet);
	
	}
	
	public void loadProductData()
	{
		ProductDTO laptop = new ProductDTO();
		laptop.setId(1l);
		laptop.setName("laptop");
		laptop.setDescription("laptop");
		laptop.setPrice(new BigDecimal(1000));
		productService.createProduct(laptop);
		
		
		ProductDTO tablet = new ProductDTO();
		tablet.setId(2l);
		tablet.setName("tablet");
		tablet.setDescription("tablet");
		tablet.setPrice(new BigDecimal(500));
		productService.createProduct(tablet);
		
		
		ProductDTO mobile = new ProductDTO();
		mobile.setId(3l);
		mobile.setName("mobile");
		mobile.setDescription("mobile");
		mobile.setPrice(new BigDecimal(10000));
		productService.createProduct(mobile);
	
		ProductDTO pendrive = new ProductDTO();
		pendrive.setId(4l);
		pendrive.setName("pendrive");
		pendrive.setDescription("pendrive");
		pendrive.setPrice(new BigDecimal(100));
		productService.createProduct(pendrive);
		
		ProductDTO car = new ProductDTO();
		car.setId(5l);
		car.setName("car");
		car.setDescription("car");
		car.setPrice(new BigDecimal(100000));
		productService.createProduct(car);
		
	}
	
	public void loadCustomerData()
	{
		CustomerDTO dhruvi = new CustomerDTO(1l, "Dhruvi", "Joshi", "dd@gmail.com", "Ahmedabad");
		customerService.createCustomer(dhruvi);
		
		CustomerDTO vrunda = new CustomerDTO(2l, "Vrunda", "Mehta", "vv@gmail.com", "Anand");
		customerService.createCustomer(vrunda);
		
		CustomerDTO vatsal = new CustomerDTO(3l, "Vatsal", "Patel", "vp@gmail.com", "Jaipur");
		customerService.createCustomer(vatsal);
		
		CustomerDTO bhavee = new CustomerDTO(4l, "Bhavee", "Joshi", "bb@gmail.com", "Ahmedabad");
		customerService.createCustomer(bhavee);
		
		CustomerDTO parth = new CustomerDTO(5l, "Parth", "Thakar", "pt@gmail.com", "Mumbai");
		customerService.createCustomer(parth);
	}
}
