package com.product.productwebservices.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.product.productwebservices.model.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {
	
	private Long id;
	
	@NotBlank(message = "Firstname cannot be blank")
	@Pattern(regexp = "[a-zA-Z]+", message = "Enter valid firstname")
	private String firstName;
	
	@NotBlank(message = "Lastname cannot be blank")
	@Pattern(regexp = "[a-zA-Z]+", message = "Enter valid lastname")
	private String lastName;
	
	@NotBlank(message = "Email cannot be blank")
	@Email(message = "Enter valid email id")
	private String email;
	private String city;
	
	//private List<ProductDTO> products;
	
	public CustomerDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public CustomerDTO(Long id, String firstName, String lastName, String email, String city) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.city = city;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", city=" + city + "]";
	}

}
