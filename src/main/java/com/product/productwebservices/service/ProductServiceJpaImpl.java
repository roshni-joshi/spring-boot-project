package com.product.productwebservices.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.product.productwebservices.dto.ProductDTO;
import com.product.productwebservices.mapper.ProductMapper;
import com.product.productwebservices.model.Product;
import com.product.productwebservices.repository.ProductRepository;

@Service
@Profile("jpa")   //@Profile("default", "jpa") to set it as default. So there is no need to mention in application.properties.
//@Primary      //@Primary and @Profile is used to say product loader class to use this implementation of product service interface.
public class ProductServiceJpaImpl implements ProductService {

	private ProductMapper productMapper;
	private ProductRepository productRepository;
	
	public ProductServiceJpaImpl(final ProductMapper productMapper, final ProductRepository productRepository) {
		this.productMapper = productMapper;
		this.productRepository = productRepository;
	}

	@Override
	public List<ProductDTO> getAllProducts() {
		System.out.println("Product JPA : Get All Products");
		
		List<ProductDTO> productDTOList = new ArrayList<>();
	
		List<Product> productList = productRepository.findAll();
		
		if(productList != null && productList.size() > 0)
		{
			productDTOList = productList.stream().map(productMapper::productToProductDTO).collect(Collectors.toList());
		}
		return productDTOList;
	}

	@Override
	public ProductDTO getProductById(Long id) {
		System.out.println("Product JPA : Get Product By Id");
		ProductDTO productDTO = null;
		
		if(id != null)
		{
			//Optional<Product> optionalProduct = productRepository.findById(id);
			Product dbproduct = productRepository.findById(id).orElseThrow(() -> new com.product.productwebservices.exception.ProductNotFoundException("No product found"));
			
			//Product dbproduct = productRepository.findById(id).orElseThrow(com.product.productwebservices.exception.ProductNotFoundException :: new);
			
			//if(optionalProduct != null && optionalProduct.isPresent())
			//{
				//Product dbproduct = optionalProduct.get();
				if(dbproduct != null)
				{
					productDTO = productMapper.productToProductDTO(dbproduct);
				}
			//}
		}
		return productDTO;
	}

	@Override
	public ProductDTO createProduct(ProductDTO productDTO) {
	
		System.out.println("Product JPA : Create Product");
		ProductDTO savedProductDTO = null;
		
		if(productDTO != null)
		{
			Product productToSave = productMapper.productDTOToProduct(productDTO); //here productDTO may or may not contains id
			if(productToSave != null)
			{
				Product savedProduct = productRepository.save(productToSave);
				
				savedProductDTO = productMapper.productToProductDTO(savedProduct); //here savedProductDTO contains id
			}
		}
		return savedProductDTO;
	}

	@Override
	public ProductDTO updateProduct(ProductDTO productDTO) {

		System.out.println("Product JPA : Update Product");
		return createProduct(productDTO);
	}

	@Override
	public void deleteProduct(Long id) {

		System.out.println("Product JPA : Delete Product By Id : " + id);
		if(id != null)
		{
			productRepository.deleteById(id);
		}
	}

}
