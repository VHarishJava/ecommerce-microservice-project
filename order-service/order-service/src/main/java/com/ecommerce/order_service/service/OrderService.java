package com.ecommerce.order_service.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.order_service.feign.NotificationClient;
import com.ecommerce.order_service.feign.ProductClient;
import com.ecommerce.order_service.model.Order;
import com.ecommerce.order_service.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductClient productClient;
	
	
	@Autowired
	private NotificationClient notificationClient;
	
	public Order placeOrder(Order order) {
        // 1. Get product details from product-service
        Map<String, Object> product = productClient.getProductById(order.getProductId());

        // 2. Calculate total price
        BigDecimal price = new BigDecimal(product.get("price").toString());
        BigDecimal totalPrice = price.multiply(BigDecimal.valueOf(order.getQuantity()));
        order.setTotalPrice(totalPrice);

        // 3. Reduce stock in product-service
        productClient.reduceStock(order.getProductId(), order.getQuantity());
        
        
        Order savedOrder = orderRepository.save(order);

        // 👇 Auto send email after order placed
        try {
            notificationClient.sendOrderConfirmation(
                order.getUsername(),
                order.getEmail(),        // add email field to Order model
                savedOrder.getId(),
                totalPrice.doubleValue()
            );
        } catch (Exception e) {
            System.out.println("Notification failed: " + e.getMessage());
        }

        
        
        

        return savedOrder;
    }

    // Get all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Get order by ID
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found!"));
    }
	
	public List<Order> getByUsername(String username){
		return orderRepository.findByUsername(username);
	}
	
	
}
