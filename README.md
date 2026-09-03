# SpringCloudPaymentCircuitBreaker

The payment service with a **Hystrix** circuit breaker around its outbound calls, so a dependency
being down degrades the response instead of hanging it.

## What is in here

- `Controller/PaymentRestController.java` — the endpoints and the Hystrix fallback
- `SpringCloudPaymentCircuitBreakerApplication.java` — Eureka client and circuit-breaker enablement
- `application.properties`:
  ```properties
  server.port=9898
  spring.application.name=PAYMENT-APP
  management.endpoints.web.exposure.include=*
  ```

Dependencies: `spring-cloud-starter-hystrix`, `spring-cloud-starter-hystrix-dashboard`,
`spring-cloud-starter-netflix-eureka-client`, `spring-boot-starter-actuator`.

## What a circuit breaker actually buys you

Without one, a dead dependency means every request waits for a timeout and a thread is tied up for
each. Enough of those and the *caller* goes down too — the failure spreads upstream.

The breaker watches the failure rate, and past a threshold stops calling and runs the fallback
immediately. The dependency is still broken; the difference is that this service stays responsive
and stops adding load to something already struggling.

## ⚠️ Hystrix is end-of-life

Netflix stopped active development. It is here because it is what the tutorials of the time taught
and the ideas transfer, but for anything real see the **`Resilience4j`** repo — same pattern,
maintained, and it covers retry, rate limiting, time limiting and bulkheads as well.

## The set this belongs to

These were written as one system, not as isolated samples. **Nothing registers until the Eureka
server is up**, so start it first.

| Repo | Port | `spring.application.name` | Role |
|---|---|---|---|
| `SpringCloudEurekaServerApplication` | 8761 | — | Service registry — **start first** |
| `SpringCloudConfigServerApplication` | 8888 | — | Git-backed configuration server |
| `SpringCloudApiGateway` | 8080 | `GATEWAY-SERVICE` | The single entry point |
| `SpringCloudCartServiceApplicationEurekaExample` | 9009 | `CART-SERVICE` | Cart and book endpoints |
| `SpringCloudPaymentServiceApplicationEurekaeg` | 8989 | `PAYMENT-SERVICE` | Payment, calling out via Feign |
| `SpringCloudProductServiceconfigclient` | 9940 | `PRODUCT-SERVICE` | Reads its config from the config server |
| `RefreshScopeApplicationProperties` | 9940 | `PRODUCT-SERVICE` | The same product service, plus `@RefreshScope` |
| `SpringCloudPaymentCircuitBreaker` | 9898 | `PAYMENT-APP` | Payment again, wrapped in Hystrix |
| `Resilience4j` | — | — | What to use instead of Hystrix, which is end-of-life |

## Running it

```bash
./mvnw spring-boot:run
```

---

One of a set of small repositories I wrote while learning the Java/Spring ecosystem. Each one
exists to get a single idea working end to end, so it is deliberately minimal — no tests worth the
name, no production hardening. Kept public because the commit history is a more honest record of
what I learned than a summary would be.
