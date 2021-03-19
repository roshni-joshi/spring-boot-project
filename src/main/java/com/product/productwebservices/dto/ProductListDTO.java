package com.product.productwebservices.dto;

import java.util.List;

public class ProductListDTO {

	private List<ProductDTO> products;

	public ProductListDTO(final List<ProductDTO> products) {
		this.products = products;
	}

	public ProductListDTO() {
		// TODO Auto-generated constructor stub
	}
	public List<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}

	
}
