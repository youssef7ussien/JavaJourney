package com.payment.springweb.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.payment.springweb.models.Payment;

@FeignClient(name = "payment", url = "${name.service.url}")
public interface OpenFeignProxy {

   @PostMapping("api/payment")
   Payment createPayment(
         @RequestHeader String requestId,
         @RequestHeader String clientType,
         @RequestBody Payment payment);

}
