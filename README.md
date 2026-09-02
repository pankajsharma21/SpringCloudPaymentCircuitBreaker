# SpringCloudPaymentCircuitBreaker

The circuit-breaker piece of a Spring Cloud microservice set: a payment service that registers with
Eureka and wraps its calls in Hystrix, so a dependency being down degrades the response instead of
hanging it.

## What is in here

- `Controller/PaymentRestController.java` — the endpoints, with the Hystrix fallback
- `SpringCloudPaymentCircuitBreakerApplication.java` — Eureka client and circuit-breaker enablement
- `application.properties` — the Eureka registration and Hystrix settings

Dependencies: `spring-cloud-starter-netflix-eureka-client`, `spring-cloud-starter-hystrix`,
`spring-cloud-starter-hystrix-dashboard`, and `spring-boot-starter-actuator`.

## It needs the rest of the set

This service is not standalone. Start **`SpringCloudEurekaServerApplication`** first, or this one
has nowhere to register. The related repos:

| Repo | Role |
|---|---|
| `SpringCloudEurekaServerApplication` | Service registry — start this first |
| `SpringCloudConfigServerApplication` | Centralised configuration |
| `SpringCloudApiGateway` | The edge |
| `SpringCloudCartServiceApplicationEurekaExample` | A registered service |
| `SpringCloudPaymentServiceApplicationEurekaeg` | The payment service without the breaker |
| `Resilience4j` | The same idea after Hystrix went end-of-life |

**A note on Hystrix:** it is no longer maintained. `Resilience4j` in the list above is the
current answer, and comparing the two is more useful than reading either alone.

### Running it

```bash
./mvnw spring-boot:run
```
Then open http://localhost:8080.

---

One of a set of small repositories I wrote while learning the Java/Spring ecosystem. Each one
exists to get a single idea working end to end, so it is deliberately minimal — no tests worth the
name, no production hardening. Kept public because the commit history is a more honest record of
what I learned than a summary would be.
