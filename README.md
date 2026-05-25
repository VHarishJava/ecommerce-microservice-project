# E-Commerce Microservices Project
Java Spring Boot Microservices E-commerce Backend with JWT, Eureka, OpenFeign, MySQL

A microservices-based e-commerce backend application built using Java Spring Boot.

## Architecture
- Eureka Server (Service Discovery)
- User Service (JWT Authentication + BCrypt)
- Product Service (Product CRUD APIs)
- Order Service (Order Processing + OpenFeign Client)
- Notification Service (Email Notifications via Gmail SMTP)

## Tech Stack
- Java 17
- Spring Boot
- Spring Security
- JWT
- BCrypt
- Spring Cloud Eureka
- Feign Client
- MySQL
- Maven
- REST APIs

## Features
User Registration & Login  
JWT Authentication  
Product Management APIs  
Order Placement  
Inter-service Communication  
Email Notification Service  
Service Discovery with Eureka  

## Architecture Flow

Client Request
   ↓
User Service (JWT Authentication)
   ↓
Order Service (Business Logic)
   ↓
Product Service (Product Details via OpenFeign)
   ↓
Notification Service (Email Alerts)

Eureka Server → Service Discovery

