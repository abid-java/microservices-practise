package com.app.springcloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.springcloud.entity.Product;
import com.app.springcloud.repository.ProductRepo;

@RestController
@RequestMapping("/productapi")
public class ProductController {

	Logger logger = LoggerFactory.getLogger(ProductController.class.getName());

	@Autowired
	private ProductRepo productRepo;

	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		ResponseEntity<Product> responseEntity = null;
		Product created = null;
		try {
			if(product != null) {
				created = productRepo.save(product);
				responseEntity = new ResponseEntity<Product>(created, HttpStatus.OK);
			}
		} catch(Exception exception) {
			logger.error("Exception while saving Product Details : " + exception.getMessage());
			exception.printStackTrace();
		}		
		return responseEntity;
	}
	
	@RequestMapping(value = "/products/{name}", method = RequestMethod.GET)
	public ResponseEntity<Product> getProduct(@PathVariable("name") String name) {
		Product product = null;
		ResponseEntity<Product> responseEntity = null;
		try {
			product = productRepo.findByName(name);
			responseEntity  = new ResponseEntity<Product>(product, HttpStatus.OK);
		} catch(Exception exception) {
			logger.error("Exception while saving Product Details : " + exception.getMessage());
			exception.printStackTrace();
		}		
		return responseEntity;
	}
}
