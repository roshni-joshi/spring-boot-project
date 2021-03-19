package com.product.productwebservices.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ProductExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<Object> handleProductNotFoundException(Exception exception, WebRequest request)
	{
		return new ResponseEntity<Object>("Error occured while fetching the products: " + exception.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<Object> handleNumberFormatException(Exception exception, WebRequest request)
	{
		return new ResponseEntity<Object>("Error occured while processing the input parameters: " + exception.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ProductValidationException.class)
	public ResponseEntity<Object> handleProductValidationException(Exception exception, WebRequest request)
	{
		return new ResponseEntity<Object>("Error occured while creating/updating the product: " + exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

}
