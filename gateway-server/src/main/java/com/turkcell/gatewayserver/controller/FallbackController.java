package com.turkcell.gatewayserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fallback")
public class FallbackController
{

    @GetMapping("/identity-service")
    public Mono<String> identityServiceFallback()
    {
        return Mono.just("identity-service geçici olarak kullanılamıyor, lütfen daha sonra tekrar deneyin.");
    }

    @GetMapping("/customer-service")
    public Mono<String> customerServiceFallback()
    {
        return Mono.just("customer service geçici olarak kullanılamıyor");
    }

}
