package com.product.productwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.productwebservices.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findByName(String name);   //no need to provide implementation. Jpa will take care of it. 'findBy' is used as a clause here
}
