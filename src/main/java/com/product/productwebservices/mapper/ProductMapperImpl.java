package com.product.productwebservices.mapper;

import org.springframework.stereotype.Component;

import com.product.productwebservices.dto.ProductDTO;
import com.product.productwebservices.model.Product;

@Component
public class ProductMapperImpl implements ProductMapper {

	private CustomerMapper customerMapper;
	
	public ProductMapperImpl(CustomerMapper customerMapper) {
		super();
		this.customerMapper = customerMapper;
	}

	@Override
	public ProductDTO productToProductDTO(Product product) {
		// TODO Auto-generated method stub
		if(product == null)
		{
			return null;
		}
		
		ProductDTO productDto = new ProductDTO();
		productDto.setId(product.getId());
		productDto.setName(product.getName());
		productDto.setDescription(product.getDescription());
		productDto.setPrice(product.getPrice());
		productDto.setCustomer(customerMapper.customerToCustomerDTO(product.getCustomer()));;
		
		return productDto;
	}

	@Override
	public Product productDTOToProduct(ProductDTO productDTO) {
		// TODO Auto-generated method stub
		
		if(productDTO == null)
		{
			return null;
		}
		
		Product product = new Product();
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setDescription(productDTO.getDescription());
		product.setPrice(productDTO.getPrice());
		product.setCustomer(customerMapper.customerDTOToCustomer(productDTO.getCustomer()));
		
		return product;
	}

}
