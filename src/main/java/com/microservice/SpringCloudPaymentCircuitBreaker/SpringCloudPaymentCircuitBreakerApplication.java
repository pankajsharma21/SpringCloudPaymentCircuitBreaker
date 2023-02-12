package com.microservice.SpringCloudPaymentCircuitBreaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
//@EnableTurbine
@EnableHystrix
@EnableHystrixDashboard
@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudPaymentCircuitBreakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudPaymentCircuitBreakerApplication.class, args);
	}

}
