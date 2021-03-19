package com.product.productwebservices.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.productwebservices.dto.ProductDTO;
import com.product.productwebservices.dto.ProductListDTO;
import com.product.productwebservices.exception.ProductValidationException;
import com.product.productwebservices.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	private static final String PIPE = "|";
	private ProductService productService;

	public ProductController(final ProductService productService) {
		super();
		this.productService = productService;
	}


	/*@RequestMapping("/")
	public String greet()
	{
		return "welcome";
	}*/
	
	@RequestMapping({"/", ""})
	public ProductListDTO getAllProducts()
	{
		return new ProductListDTO(productService.getAllProducts());
	}
	
	@RequestMapping("/{id}")
	public ProductDTO getProductById(@PathVariable("id") final String id)
	{
		return productService.getProductById(Long.valueOf(id));
	}
	
	@PostMapping({"/",""})
	public ProductDTO createProduct(@Valid final @RequestBody ProductDTO productDTO, final BindingResult bindingResult)
	{
		StringBuilder stringBuilder = new StringBuilder();
		if(bindingResult != null && bindingResult.hasErrors())
		{
			bindingResult.getAllErrors().forEach(error -> {
				stringBuilder.append(PIPE);
				stringBuilder.append(error.getDefaultMessage());
			});
			
			String validationErrors = stringBuilder.toString();
			System.out.println(validationErrors);
			throw new ProductValidationException(validationErrors);
		}
		return productService.createProduct(productDTO);
	}
	
	@PutMapping({"/{id}"})
	public ProductDTO updateProduct(@PathVariable("id") final String id, final @RequestBody ProductDTO productDTO)
	{
		ProductDTO updatedProduct = null;
		if(productDTO != null)
		{
			if(id != null)
			{
				productDTO.setId(Long.valueOf(id));
			}
			updatedProduct = productService.updateProduct(productDTO);
		}

		return updatedProduct;
	}
	
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable("id") final String id)
	{
		if(id != null)
		{
			productService.deleteProduct(Long.valueOf(id));
		}
	}
}
