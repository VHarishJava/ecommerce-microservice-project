package com.ecommerce.product_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.product_service.model.Product;
import com.ecommerce.product_service.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public List<Product> getAllProducts(){
		return productService.getAllProduct();
	}

	@GetMapping("/{id}")
	public Product getProductById(@PathVariable Long id) {
		return productService.getProductById(id);
	}
	
	
	@PostMapping
	public Product createProduct(@RequestBody Product product) {
		return productService.createProduct(product);
	}
	
	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable Long id) {
		return productService.deleteProduct(id);
	}
	
	@PutMapping("/{id}")
	public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
		return productService.updateProduct(id, product);
	}
		
	
	@PutMapping("/{id}/reduce-stock/{quantity}")
	public String reduceStock(@PathVariable Long id, @PathVariable Integer quantity) {
		return productService.reduceStock(id, quantity);
	}
	
	
	
	
}
