package com.rcApp.gatewayService.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Configuration
public class MyResilience4JConfiguration {

    private final static Duration TIME_LIMITER = Duration.ofSeconds(10);

    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> breakerFactoryCustomizer() {
        // todo implementirati circuit breaker na endpointe i failbacaks endpoint
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .circuitBreakerConfig(CircuitBreakerConfig.custom()
                        .slidingWindowSize(20)
                        .permittedNumberOfCallsInHalfOpenState(5)
                        .failureRateThreshold(50)
                        .waitDurationInOpenState(Duration.ofSeconds(30))
                        .build())
                .timeLimiterConfig(TimeLimiterConfig.custom()
                        .timeoutDuration(TIME_LIMITER)
                        .build())
                .build());
    }

    @Bean
    KeyResolver userKeyResolver() {
        return exchange -> {
            // todo bolji nacin za key value y redis bazi
          return   Mono.just(exchange.getRequest().getRemoteAddress().toString());};
    }
}
