package com.product.productwebservices.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<Object> handleCustomerNotFoundException(Exception exception, WebRequest request)
	{
		return new ResponseEntity<Object>("Error occured while fetching the customers: " + exception.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CustomerValidationException.class)
	public ResponseEntity<Object> handleCustomerValidationException(Exception exception, WebRequest request)
	{
		return new ResponseEntity<Object>("Error occured while creating/updating the customer: " + exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
}
