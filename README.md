# Quiz Application – Microservices Architecture
A modular quiz management system built using Spring Boot and Spring Cloud Microservices, designed to demonstrate real-world microservices concepts such as service registry, load balancing, and centralized routing.

# Tech Stack
* Java 21, Spring Boot
* Spring Cloud Netflix Eureka
* Open Feign
* Spring Cloud Gateway
* RESTful Web Services
* Maven, Docker, Lombok

# Microservices Overview
# Question Service
Exposes REST endpoints to manage and retrieve quiz questions.
Stores sample questions categorized by topic (e.g., Java, Python, C++).

# Quiz Service
Calls Question Service via REST to generate quiz sets dynamically.
Business logic: fetches questions from Question Service and constructs a quiz.

# Eureka Service Registry
Registers all microservices dynamically.
Enables service discovery and load-balanced communication.
URL: http://localhost:8761

# API Gateway
Acts as a unified entry point for all services.
Routes external requests to internal services.

# Project Structure
Quiz-Application/
│
├── API-Gateway/
├── quiz/
├── question-service/
├── service-registry/
└── README.md

# Key Learnings
Designed scalable architecture using microservices principles.

Mastered Spring Cloud Eureka for service discovery and resilience.

Used centralized gateway to secure and manage routes between services.

Practiced inter-service communication with REST templates.

Applied clean code principles and modular project separation.
