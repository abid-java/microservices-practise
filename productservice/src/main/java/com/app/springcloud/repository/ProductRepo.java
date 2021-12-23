package com.app.springcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.springcloud.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

	Product findByName(String name);

}
