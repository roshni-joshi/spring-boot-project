package com.product.productwebservices.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import javax.print.attribute.standard.MediaSize.NA;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.product.productwebservices.dto.ProductDTO;
import com.product.productwebservices.mapper.CustomerMapperImpl;
import com.product.productwebservices.mapper.ProductMapperImpl;

class ProductServiceMapImplTest {

	private ProductService productService;
	
	private static final Long ID = 1l;
	private static final String NAME = "laptop";
	private static final String DESCRIPTION = "laptop";
	private static final BigDecimal PRICE = new BigDecimal(100);
	
	@BeforeEach
	void setUp() throws Exception {
		
		productService = new ProductServiceMapImpl(new ProductMapperImpl(new CustomerMapperImpl()));
		ProductDTO productDTO = new ProductDTO();
		
		productDTO.setId(ID);
		productDTO.setName(NAME);
		productDTO.setDescription(DESCRIPTION);
		productDTO.setPrice(PRICE);
		
		productService.createProduct(productDTO);
	}

	@Test
	void testGetAllProducts() {
		
		List<ProductDTO> productDTOList = productService.getAllProducts();
		assertEquals(1, productDTOList.size());
		assertEquals(ID, productDTOList.get(0).getId());
		assertEquals(NAME, productDTOList.get(0).getName());
		assertEquals(DESCRIPTION, productDTOList.get(0).getDescription());
		assertEquals(PRICE, productDTOList.get(0).getPrice());
	}
	
	@Test
	void testgetProductById()
	{
		ProductDTO productDTO = productService.getProductById(ID);
		assertNotNull(productDTO);
		assertEquals(ID, productDTO.getId());
		assertEquals(NAME, productDTO.getName());
		assertEquals(DESCRIPTION, productDTO.getDescription());
		assertEquals(PRICE, productDTO.getPrice());
	}
	
	@Test
	void testgetProductByNonExistenceId()
	{
		ProductDTO productDTO = productService.getProductById(5l);
		assertNull(productDTO);
	}
	
	@Test
	void testcreateProduct()
	{
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(2l);
		productDTO.setName(NAME);
		productDTO.setDescription(DESCRIPTION);
		productDTO.setPrice(PRICE);
		
		ProductDTO resultDTO = productService.createProduct(productDTO);
		
		assertNotNull(resultDTO);
		assertEquals(2l, resultDTO.getId());
		assertEquals(NAME, resultDTO.getName());
		assertEquals(DESCRIPTION, resultDTO.getDescription());
		assertEquals(PRICE, resultDTO.getPrice());
	}
	
	@Test
	void testcreateProductForNullValue()
	{
		ProductDTO resultDTO = productService.createProduct(null);		
		assertNull(resultDTO);
	}
	
	@Test
	void testcreateProductWithoutId()
	{
		ProductDTO productDTO = new ProductDTO();
		productDTO.setName(NAME);
		productDTO.setDescription(DESCRIPTION);
		productDTO.setPrice(PRICE);
		
		ProductDTO resultDTO = productService.createProduct(productDTO);
		
		assertNotNull(resultDTO);
		assertEquals(ID + 1, resultDTO.getId());
		assertEquals(NAME, resultDTO.getName());
		assertEquals(DESCRIPTION, resultDTO.getDescription());
		assertEquals(PRICE, resultDTO.getPrice());
	}
	

	@Test
	void testupdateProduct()
	{
		ProductDTO productDTO = productService.getProductById(ID);
		productDTO.setName("mobile");
		
		ProductDTO resultDTO = productService.updateProduct(productDTO);
		
		assertNotNull(resultDTO);
		assertEquals(ID, resultDTO.getId());
		assertEquals("mobile", resultDTO.getName());
		assertEquals(DESCRIPTION, resultDTO.getDescription());
		assertEquals(PRICE, resultDTO.getPrice());
	}
	
	@Test
	void testdeleteProduct()
	{
		assertNotNull(productService.getProductById(ID));
		productService.deleteProduct(ID);
		assertNull(productService.getProductById(ID));
	}
	
}
