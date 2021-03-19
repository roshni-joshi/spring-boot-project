package com.product.productwebservices.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.product.productwebservices.dto.ProductDTO;
import com.product.productwebservices.mapper.ProductMapper;
import com.product.productwebservices.model.Product;

@Service
@Profile("map")
public class ProductServiceMapImpl implements ProductService {
	
	private Map<Long, Product> 	productMap = new HashMap<>();
	
	private ProductMapper productMapper;
	
	public ProductServiceMapImpl(ProductMapper productMapper) {
		this.productMapper = productMapper;
	}

	@Override
	public List<ProductDTO> getAllProducts() {
		
		System.out.println("Product Map : Get All Products");
		List<ProductDTO> productDTOList = new ArrayList<>();
		
		if(productMap != null && productMap.size() > 0)
		{
			productDTOList = productMap.values().stream().map(productMapper::productToProductDTO).collect(Collectors.toList());
		}
		return productDTOList;
	}

	@Override
	public ProductDTO getProductById(Long id) {
		System.out.println("Product Map : Get Product By Id");
		ProductDTO productDTO = null;
		if(id != null)
		{
			if(productMap != null && productMap.containsKey(id))
			{
				Product product = productMap.get(id);
				if(product != null)
				{
					productDTO = productMapper.productToProductDTO(product);
				}
			}
		}
		return productDTO;
	}

	@Override
	public ProductDTO createProduct(ProductDTO productDTO) {
		System.out.println("Product Map : Create Product");
		ProductDTO savedDTO = null;
		
		if(productDTO != null)
		{
			if(productDTO.getId() == null)
			{
				productDTO.setId(getNextId());
			}
			
			Product product = productMapper.productDTOToProduct(productDTO);
			if(product != null)
			{
				productMap.put(product.getId(), product);
				savedDTO = productMapper.productToProductDTO(product);
			}
		}
		return savedDTO;
	}

	@Override
	public ProductDTO updateProduct(ProductDTO productDTO) {
		System.out.println("Product Map : Update Product");
		return createProduct(productDTO);
	}

	@Override
	public void deleteProduct(Long id) {

		System.out.println("Product Map : Delete Product By Id : " + id);
		if(id != null && productMap.containsKey(id))
		{
			productMap.remove(id);
		}
	}
	
	private Long getNextId()
	{
		Long id = null;
		
		if(productMap != null)
		{
			if(productMap.size() == 0)
			{
				id = 1l;
			}
			else
			{
				id = Collections.max(productMap.keySet()) + 1;
			}
		}
		return id;
	}

}
