package com.turkcell.gatewayserver.filter;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class RateLimitConfiguration {

    @Bean
    KeyResolver ipKeyResolver() {
        return exchange ->
        {
            String ipAddress = exchange.getRequest().getHeaders().getFirst("X-Forwarded-For");

            if (ipAddress == null && exchange.getRequest().getRemoteAddress() != null) {
                ipAddress = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
            }

            return Mono.just(ipAddress != null ? ipAddress : "unknown");
        };
    }

}
