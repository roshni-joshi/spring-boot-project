package com.product.productwebservices.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.product.productwebservices.dto.ProductDTO;
import com.product.productwebservices.mapper.CustomerMapperImpl;
import com.product.productwebservices.mapper.ProductMapper;
import com.product.productwebservices.mapper.ProductMapperImpl;
import com.product.productwebservices.model.Product;
import com.product.productwebservices.repository.ProductRepository;

class ProductServiceJpaImplTest {
	
	private static final String MOBILE = "mobile";
	private static final String LAPTOP = "laptop";
	private ProductService productService;
	private ProductMapper productMapper;
	
	private Product product1;
	private Product product2;
	
	@Mock
	private ProductRepository productRepository;

	@BeforeEach
	void setUp() throws Exception {
		
		MockitoAnnotations.openMocks(this);
		productMapper = new ProductMapperImpl(new CustomerMapperImpl());
		productService = new ProductServiceJpaImpl(productMapper, productRepository);
		loadProducts();
	}
	
	private void loadProducts()
	{
		product1 = new Product();
		product1.setId(1l);
		product1.setName(LAPTOP);
		product1.setDescription(LAPTOP);
		product1.setPrice(new BigDecimal(10));
		
		product2 = new Product();
		product2.setId(2l);
		product2.setName(MOBILE);
		product2.setDescription(MOBILE);
		product2.setPrice(new BigDecimal(100));
		
	}

	@Test
	void testGetAllProducts() {
		
		when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));
		
		List<ProductDTO> products = productService.getAllProducts();
		assertEquals(2, products.size());
		
	}

	@Test
	void testGetProductById() {
		
		Optional<Product> optionalProduct = Optional.of(product1);
		//when(productRepository.findById(1l)).thenReturn(optionalProduct);
		when(productRepository.findById(anyLong())).thenReturn(optionalProduct);
		
		ProductDTO product = productService.getProductById(1l);
		assertNotNull(product);
		assertEquals(1l, product.getId());
		assertEquals(LAPTOP, product.getName());
		assertEquals(LAPTOP, product.getDescription());
		assertEquals(new BigDecimal(10), product.getPrice());
		
	}

	@Test
	void testCreateProduct() {
		
		ProductDTO productDTO = new ProductDTO(1l, "demo", "demo", BigDecimal.TEN);
		Product product = productMapper.productDTOToProduct(productDTO);
	
		when(productRepository.save(any())).thenReturn(product);
		
		ProductDTO createdDTO = productService.createProduct(productDTO);
		
		assertNotNull(createdDTO);
		assertEquals(1l, createdDTO.getId());
		assertEquals(product.getName(), createdDTO.getName());
	
	}
	
	@Test
	void testCreateProductWithoutSettingId() {
		
		ProductDTO productDTO = new ProductDTO(null, "demo", "demo", BigDecimal.TEN);
		Product product = productMapper.productDTOToProduct(productDTO);
	
		product.setId(1l);
		when(productRepository.save(any())).thenReturn(product);
		
		ProductDTO createdDTO = productService.createProduct(productDTO);
		
		assertNotNull(createdDTO);
		assertEquals(1l, createdDTO.getId());
		assertEquals(product.getName(), createdDTO.getName());
		

	}

	@Test
	void testUpdateProduct() {
		product1.setName("demo");
		
		when(productRepository.save(any())).thenReturn(product1);
		
		ProductDTO productDTO = productService.updateProduct(productMapper.productToProductDTO(product1));
		
		assertNotNull(productDTO);
		assertEquals(product1.getId(), productDTO.getId());
		assertEquals("demo", productDTO.getName());
	}

	@Test
	void testDeleteProduct() {
		
		productRepository.deleteById(1l);
		
		verify(productRepository, times(1)).deleteById(anyLong());
	}

}
