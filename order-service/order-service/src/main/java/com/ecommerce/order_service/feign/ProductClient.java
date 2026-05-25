package com.ecommerce.order_service.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name="product-service")
public interface ProductClient {
	
	@GetMapping("/products/{id}")
	Map<String,Object> getProductById(@PathVariable Long id);

	
	@PutMapping("/products/{id}/reduce-stock/{quantity}")
    String reduceStock(@PathVariable Long id, @PathVariable int quantity);
}
