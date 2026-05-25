package com.ecommerce.notification_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.notification_service.model.NotificationRequest;
import com.ecommerce.notification_service.service.NotificationService;

@RestController
@RequestMapping("/notification")
public class NotificationController {
	
	@Autowired
	private NotificationService notificationService;
	
	
    // Send custom notification
    @PostMapping("/send")
    public String sendNotification(@RequestBody NotificationRequest request) {
        return notificationService.sendNotification(request);
    }

    // Send order confirmation
    @PostMapping("/order-confirmation")
    public String orderConfirmation(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam Long orderId,
            @RequestParam double totalPrice) {
        return notificationService.sendOrderConfirmation(
            username, email, orderId, totalPrice);
    }

}
