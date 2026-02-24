# Tributary Bank

Tributary Bank is a sample banking system built with Spring Boot and Spring Cloud.  

## Components

| Service               | Port | Description |
|-----------------------|------|-------------|
| **Eureka Server**     | 8761 | Service discovery registry for all services |
| **Config Server**     | 8787 | Centralized configuration management (backed by Git or local files) |
| **Gateway**           | 8888 | API Gateway with routing, load balancing and edge concerns |
| **Identity Service**  | 8082 | Handles user authentication and authorization (issuing / validating tokens) |
| **Customer Service**  | 8081 | Manages customer data and exposes customer-related operations |

## Spring Cloud Features Demonstrated

- **Service Discovery** – Netflix Eureka
- **Centralized Config** – Spring Cloud Config Server
- **Client-Side Load Balancing** – Spring Cloud LoadBalancer
- **API Gateway** – Spring Cloud Gateway
- **Circuit Breaker / Resilience** – Resilience4j

## Architecture Overview

- **Gateway-first access**: Clients call only the `Gateway` on port `8888`; it forwards requests to downstream services.
- **Service registration**: `Identity Service` and `Customer Service` register themselves with `Eureka Server`.
- **Externalized configuration**: All services fetch their configuration (profiles, DB URLs, feature flags, etc.) from the `Config Server`.
- **Resilience**: Downstream calls are wrapped with circuit breakers and timeouts using Resilience4j.

## Prerequisites

- **Java**: 21+
- **Maven**: 3.8+
- (Optional) **Docker**: if you plan to containerize or run backing services via Docker

## Getting Started

### 1. Clone the repository

```bash
git clone <this-repo-url>
cd tributary-bank
```

### 2. Build all services

```bash
mvn clean install
```

> You can also build individual modules from their own directories with `mvn clean install`.

### 3. Start the infrastructure services

Recommended order:

1. **Config Server** (port `8787`)
2. **Eureka Server** (port `8761`)

Each service is a Spring Boot application; you can start them either from your IDE or with Maven:

```bash
mvn spring-boot:run
```

Run the command in the corresponding module directory (e.g. `config-server`, `eureka-server`, etc.).

### 4. Start business services

After Config and Eureka are up:

- Start **Gateway** (port `8888`)
- Start **Identity Service** (port `8082`)
- Start **Customer Service** (port `8081`)

Again, from each module directory:

```bash
mvn spring-boot:run
```

### 5. Verify everything is running

- **Eureka Dashboard**: `http://localhost:8761`
- **Gateway base URL**: `http://localhost:8888`

## Development Notes

- Configuration for each service (e.g. DB credentials, feature flags) should be managed via the `Config Server`.
- Profiles such as `dev`, `test`, `prod` can be used to separate environment-specific settings.
- When adding a new microservice, make sure it:
  - Registers with **Eureka**
  - Fetches configuration from **Config Server**
  - Is reachable through the **Gateway** (route configuration)

## Roadmap / Ideas

- Add more domain services (e.g. Accounts, Transactions).
- Add security hardening for the **Identity Service**.
- Add observability stack (logs, metrics, tracing) with tools like Spring Boot Actuator, Prometheus and Grafana.
