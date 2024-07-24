package com.payment.springweb.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.springweb.models.ApiHeader;
import com.payment.springweb.models.Payment;
import com.payment.springweb.proxy.OpenFeignProxy;
import com.payment.springweb.proxy.RestTemplateProxy;
import com.payment.springweb.proxy.WebClientProxy;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/payment")
public class PaymentsController {
    private final OpenFeignProxy openFeignProxy;
    private final RestTemplateProxy restTemplateProxy;
    private final WebClientProxy webClientProxy;

    public PaymentsController(
            OpenFeignProxy openFeignProxy,
            RestTemplateProxy restTemplateProxy,
            WebClientProxy webClientProxy) {
        this.openFeignProxy = openFeignProxy;
        this.restTemplateProxy = restTemplateProxy;
        this.webClientProxy = webClientProxy;
    }

    @PostMapping("/openfeign")
    public Payment createPaymentOpenFeign(@RequestBody Payment payment) {
        ApiHeader apiHeader = new ApiHeader(ApiHeader.ClientType.OpenFeign);
        return openFeignProxy.createPayment(
                apiHeader.getId(),
                apiHeader.getClientType().toString(),
                payment);
    }

    @PostMapping("/rest_template")
    public Payment createPaymentRestTemplate(@RequestBody Payment payment) {
        return restTemplateProxy.createPayment(new ApiHeader(ApiHeader.ClientType.WebClient), payment);
    }

    @PostMapping("/web_client")
    public Mono<Payment> createPaymentWebClient(@RequestBody Payment payment) {
        return webClientProxy.createPayment(new ApiHeader(ApiHeader.ClientType.RestTemplate), payment);
    }
}
