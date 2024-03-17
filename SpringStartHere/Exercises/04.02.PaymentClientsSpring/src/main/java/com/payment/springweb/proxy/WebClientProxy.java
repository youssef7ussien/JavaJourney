package com.payment.springweb.proxy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.payment.springweb.models.ApiHeader;
import com.payment.springweb.models.Payment;

import reactor.core.publisher.Mono;

@Component
public class WebClientProxy {
    private final WebClient webClient;

    @Value("${name.service.url}")
    private String url;

    public WebClientProxy(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Payment> createPayment(ApiHeader apiHeader, Payment payment) {
        return webClient.post()
                .uri(url + "/api/payment")
                .header("requestId", apiHeader.getId())
                .header("clientType", apiHeader.getClientType().toString())
                .body(Mono.just(payment), Payment.class)
                .retrieve()
                .bodyToMono(Payment.class);
    }
}
