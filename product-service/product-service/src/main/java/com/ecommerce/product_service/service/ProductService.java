package com.ecommerce.product_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.product_service.model.Product;
import com.ecommerce.product_service.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> getAllProduct(){
		return productRepository.findAll();
	}
	
	public Product getProductById(Long id) {
		
		return productRepository.findById(id)
				.orElseThrow(()->new RuntimeException("Product not found!"));
		
	}
	
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}
	
	public Product updateProduct(Long id, Product updateProduct) {
		Product existingProduct=getProductById(id);
		existingProduct.setName(updateProduct.getName());
		existingProduct.setDescription(updateProduct.getDescription());
		existingProduct.setPrice(updateProduct.getPrice());
		existingProduct.setDescription(updateProduct.getDescription());
		
		return productRepository.save(existingProduct);
		
	}
	public String deleteProduct(Long id) {
		 productRepository.deleteById(id);
		 return "Product deleted successfully";
	}
	
	public String reduceStock(Long id ,int quantity) {
		Product product=getProductById(id);
		 if (product.getQuantity() < quantity) {
	            throw new RuntimeException("Insufficient stock!");
	        }
	        product.setQuantity(product.getQuantity() - quantity);
	        productRepository.save(product);
	        return "Stock updated!";
	}
	
}
