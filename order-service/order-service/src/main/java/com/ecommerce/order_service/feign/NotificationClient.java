package com.ecommerce.order_service.feign;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "notification-service")
public interface NotificationClient {

    @PostMapping("/notifications/order-confirmation")
    String sendOrderConfirmation(
        @RequestParam String username,
        @RequestParam String email,
        @RequestParam Long orderId,
        @RequestParam double totalPrice
    );
}