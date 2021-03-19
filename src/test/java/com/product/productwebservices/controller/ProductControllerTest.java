package com.product.productwebservices.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.productwebservices.dto.ProductDTO;
import com.product.productwebservices.service.ProductService;

class ProductControllerTest {

	private static final String HP = "hp";

	private static final String TABLET = "tablet";

	private static final String LAPTOP = "laptop";
	
	private static final String BASE_URL = "/api/products";

	private ProductController productController;
	
	@Mock
	private ProductService productService;
	
	private MockMvc mockMvc;
	
	private ProductDTO laptop;
	private ProductDTO tablet;
	
	@BeforeEach
	void setUp() throws Exception {
		
		MockitoAnnotations.openMocks(this);
		
		productController = new ProductController(productService);
		
		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
		
		loadProducts();
	}
	
	private void loadProducts()
	{
		laptop = new ProductDTO();
		laptop.setId(1l);
		laptop.setName(LAPTOP);
		laptop.setDescription(LAPTOP);
		laptop.setPrice(new BigDecimal(1000));
		
		tablet = new ProductDTO();
		tablet.setId(2l);
		tablet.setName(TABLET);
		tablet.setDescription(TABLET);
		tablet.setPrice(new BigDecimal(500));
		
	}

	@Test
	void testGetAllProducts() throws Exception {
		
		when(productService.getAllProducts()).thenReturn(Arrays.asList(laptop, tablet));
		
		mockMvc.perform(get(BASE_URL).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.products", Matchers.hasSize(2)));
		
	
	}

	@Test
	void testGetProductById() throws Exception {
	
		when(productService.getProductById(ArgumentMatchers.anyLong())).thenReturn(laptop);
		
		mockMvc.perform(get(BASE_URL + "/1").accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id", Matchers.equalTo(1)))
		.andExpect(jsonPath("$.description", Matchers.equalTo(LAPTOP)))
		.andExpect(jsonPath("$.name", Matchers.comparesEqualTo(LAPTOP)))
		.andExpect(jsonPath("$.price", Matchers.equalTo(1000)));
	}

	@Test
	void testCreateProduct() throws Exception {
		ProductDTO prod = new ProductDTO();
		prod.setDescription(HP);
		prod.setName(HP);
		prod.setPrice(new BigDecimal(15));
		
		ProductDTO created = new ProductDTO();
		created.setId(3l);
		created.setDescription(HP);
		created.setName(HP);
		created.setPrice(new BigDecimal(15));
		
		when(productService.createProduct(ArgumentMatchers.any())).thenReturn(created);
		
		mockMvc.perform(post(BASE_URL).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString(prod)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id", Matchers.equalTo(3)))
		.andExpect(jsonPath("$.name", Matchers.equalTo(HP)))
		.andExpect(jsonPath("$.description", Matchers.equalTo(HP)));

	}
	
	private String jsonString(Object object)
	{
		String jsonstr = null;
		if(object != null)
		{
			try
			{
				jsonstr = new ObjectMapper().writeValueAsString(object);
			}
			catch(Exception e) {}
		}
		return jsonstr;
	}

	
	 @Test void testUpdateProduct() throws Exception {
		 
		 laptop.setDescription("new desc");
		 
		 when(productService.updateProduct(any())).thenReturn(laptop);
		 
		 mockMvc.perform(put(BASE_URL + "/1").accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString(laptop)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", Matchers.equalTo(1)))
			.andExpect(jsonPath("$.description", Matchers.equalTo("new desc")))
			.andExpect(jsonPath("$.name", Matchers.comparesEqualTo(LAPTOP)))
			.andExpect(jsonPath("$.price", Matchers.equalTo(1000)));
	
	 }
	

	@Test
	void testDeleteProduct() throws Exception {

		mockMvc.perform(delete(BASE_URL+"/25"));
		verify(productService, times(1)).deleteProduct(25l);
	}

}
