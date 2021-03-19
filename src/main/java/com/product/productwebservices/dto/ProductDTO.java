package com.product.productwebservices.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.product.productwebservices.model.Customer;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ProductDTO {

	private Long id;
	
	@NotBlank(message = "{name.notblank}")
	@Size(min = 2, max = 20, message = "{name.size}")
	@Pattern(regexp = "[a-zA-Z]+", message = "Product name should contain only alphabets")
	private String name;
	
	@NotBlank(message = "{desc.notblank}")
	@Size(min = 2, max = 20, message = "Product description should be 2-50 characters long")
	private String description;
	
	private BigDecimal price;
	private CustomerDTO customer;
	
	public ProductDTO() {
		// TODO Auto-generated constructor stub
	}

	public ProductDTO(final Long id, final String name, final String description, final BigDecimal price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", description=" + description + ", price=" + price + "]";
	}
	
	
}
