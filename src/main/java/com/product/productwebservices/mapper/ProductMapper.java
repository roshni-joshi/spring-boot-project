package com.product.productwebservices.mapper;

import com.product.productwebservices.dto.ProductDTO;
import com.product.productwebservices.model.Product;

public interface ProductMapper {

	ProductDTO productToProductDTO(final Product product);
	
	Product productDTOToProduct(final ProductDTO productDTO);
}
