package com.product.productwebservices.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.product.productwebservices.dto.ProductDTO;
import com.product.productwebservices.mapper.ProductMapper;
import com.product.productwebservices.mapper.ProductMapperImpl;
import com.product.productwebservices.model.Product;

class ProductMapperImplTest {

	private ProductMapper productMapper;
	private static final Long ID = 10l;
	private static final String NAME = "laptop";
	private static final String DESCRIPTION = "laptop";
	private static final BigDecimal PRICE = new BigDecimal(100);
	
	@Test
	public void testNullProduct()
	{
		ProductDTO productDTO = productMapper.productToProductDTO(null);
		assertNull(productDTO);
	}
	
	@Test
	public void testEmptyProduct()
	{
		Product product = new Product();
		ProductDTO productDTO = productMapper.productToProductDTO(product);
		
		assertNotNull(productDTO);
		assertNull(productDTO.getId());
		assertNull(productDTO.getName());
		assertNull(productDTO.getDescription());
		assertNull(productDTO.getPrice());
	}
	
	@Test
	public void testEmptyProductDTO()
	{
		ProductDTO productDTO = new ProductDTO();
		Product product = productMapper.productDTOToProduct(productDTO);
		
		assertNotNull(product);
		assertNull(product.getId());
		assertNull(product.getName());
		assertNull(product.getDescription());
		assertNull(product.getPrice());
	}
	
	
	@Test
	public void testNullProductDTO()
	{
		Product product = productMapper.productDTOToProduct(null);
		assertNull(product);
	}
	
	
	@BeforeEach
	void setUp() throws Exception {
		productMapper = new ProductMapperImpl(new CustomerMapperImpl());
	}

	@Test
	void testProductToProductDto() {
		Product product = new Product();
		
		product.setId(ID);
		product.setName(NAME);
		product.setDescription(DESCRIPTION);
		product.setPrice(PRICE);
		
		ProductDTO productDTO = productMapper.productToProductDTO(product);
		
		assertNotNull(productDTO);
		assertEquals(ID, productDTO.getId());
		assertEquals(NAME, productDTO.getName());
		assertEquals(DESCRIPTION, productDTO.getDescription());
		assertEquals(PRICE, productDTO.getPrice());
		
	}

	@Test
	void testProductDTOToProduct() {
		ProductDTO productDTO = new ProductDTO();
		
		productDTO.setId(ID);
		productDTO.setName(NAME);
		productDTO.setDescription(DESCRIPTION);
		productDTO.setPrice(PRICE);
		
		Product product = productMapper.productDTOToProduct(productDTO);
		
		assertNotNull(product);
		assertEquals(ID, product.getId());
		assertEquals(NAME, product.getName());
		assertEquals(DESCRIPTION, product.getDescription());
		assertEquals(PRICE, product.getPrice());
		
	}

}
