package com.ecommerce.notification_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ecommerce.notification_service.model.NotificationRequest;

import jakarta.mail.internet.MimeMessage;

@Service
public class NotificationService {
	
	
	  @Autowired
	    private JavaMailSender mailSender;

	    @Value("${spring.mail.username}")
	    private String fromEmail;

	 public String sendNotification(NotificationRequest request) {
	        try {
	            MimeMessage message = mailSender.createMimeMessage();
	            MimeMessageHelper helper = new MimeMessageHelper(message, true);

	            helper.setFrom(fromEmail);
	            helper.setTo(request.getEmail());
	            helper.setSubject("Notification from E-Commerce App");
	            helper.setText(buildEmailBody(request.getUsername(), request.getMessage()), true);

	            mailSender.send(message);
	            System.out.println("✅ Email sent to: " + request.getEmail());
	            return "Notification sent to " + request.getEmail();

	        } catch (Exception e) {
	            System.out.println("❌ Failed to send email: " + e.getMessage());
	            return "Failed to send notification: " + e.getMessage();
	        }
	    }

	    // Send order confirmation email
	    public String sendOrderConfirmation(String username, String email,
	                                        Long orderId, double totalPrice) {
	        try {
	            MimeMessage message = mailSender.createMimeMessage();
	            MimeMessageHelper helper = new MimeMessageHelper(message, true);

	            helper.setFrom(fromEmail);
	            helper.setTo(email);
	            helper.setSubject("Order Confirmed! #" + orderId);
	            helper.setText(buildOrderEmail(username, orderId, totalPrice), true);

	            mailSender.send(message);
	            System.out.println("✅ Order confirmation sent to: " + email);
	            return "Order confirmation sent for Order #" + orderId;

	        } catch (Exception e) {
	            System.out.println("❌ Failed: " + e.getMessage());
	            return "Failed: " + e.getMessage();
	        }
	    }
	    
	    
	    
	    // HTML email body for notification
	    private String buildEmailBody(String username, String message) {
	        return "<div style='font-family:Arial; padding:20px; background:#f4f4f4'>"
	            + "<div style='background:white; padding:20px; border-radius:8px'>"
	            + "<h2 style='color:#4CAF50'>Hello, " + username + "!</h2>"
	            + "<p style='font-size:16px'>" + message + "</p>"
	            + "<hr/>"
	            + "<p style='color:gray; font-size:12px'>E-Commerce App</p>"
	            + "</div></div>";
	    }
	    
	    
	 // HTML email body for order confirmation
	    private String buildOrderEmail(String username, Long orderId, double totalPrice) {
	        return "<div style='font-family:Arial; padding:20px; background:#f4f4f4'>"
	            + "<div style='background:white; padding:20px; border-radius:8px'>"
	            + "<h2 style='color:#4CAF50'>Order Confirmed! 🎉</h2>"
	            + "<p>Hi <b>" + username + "</b>, your order has been placed!</p>"
	            + "<table style='width:100%; border-collapse:collapse'>"
	            + "<tr style='background:#4CAF50; color:white'>"
	            + "<th style='padding:10px'>Order ID</th>"
	            + "<th style='padding:10px'>Total Price</th>"
	            + "<th style='padding:10px'>Status</th>"
	            + "</tr>"
	            + "<tr style='text-align:center'>"
	            + "<td style='padding:10px'>#" + orderId + "</td>"
	            + "<td style='padding:10px'>₹" + totalPrice + "</td>"
	            + "<td style='padding:10px; color:green'><b>PLACED ✅</b></td>"
	            + "</tr>"
	            + "</table>"
	            + "<p style='color:gray; font-size:12px; margin-top:20px'>Thank you for shopping with us!</p>"
	            + "</div></div>";
	    }
	    
	    
	    
}