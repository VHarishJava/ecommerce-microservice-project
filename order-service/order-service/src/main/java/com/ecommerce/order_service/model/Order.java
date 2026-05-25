package com.ecommerce.order_service.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

@Entity
@Table(name="orders")
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Long productId;
	private String username;
	private Integer quantity;
	private BigDecimal totalPrice;
	private String status;
	private LocalDateTime createdAt;
	private Email email;
	
	@PrePersist
	public void prePersist() {
		this.createdAt=LocalDateTime.now();
		this.status="PLACED";
	}

	

	public Order(Long id, Long productId, String username, Integer quantity, BigDecimal totalPrice, String status,
			LocalDateTime createdAt, Email email) {
		super();
		this.id = id;
		this.productId = productId;
		this.username = username;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.status = status;
		this.createdAt = createdAt;
		this.email = email;
	}



	public Order() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}
	
	
	
	
	
}
