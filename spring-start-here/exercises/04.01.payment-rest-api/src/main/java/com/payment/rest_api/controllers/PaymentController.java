package com.payment.rest_api.controllers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.rest_api.models.Payment;
import com.payment.rest_api.services.PaymentService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    private final PaymentService paymentService;

    private static Logger logger = Logger.getLogger(PaymentController.class.getName());

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Endpoint to create a new payment.
     *
     * @param payment The payment object to be created.
     * @return ResponseEntity with the created payment object and HTTP status.
     */
    @PostMapping
    public ResponseEntity<Payment> createPayment(
            @RequestHeader String requestId,
            @RequestHeader String clientType,
            @RequestBody Payment payment) {

        logger.info("\nRecevid request with ID: (" + requestId + ") by client type: (" + clientType + ") with " + payment);

        Payment createdPayment = paymentService.createPayment(payment);

        return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
    }

    /**
     * Endpoint to retrieve a payment by its ID.
     *
     * @param id The ID of the payment to retrieve.
     * @return ResponseEntity with the retrieved payment object and HTTP status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null) {
            return new ResponseEntity<>(payment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint to retrieve all payments.
     *
     * @return ResponseEntity with the list of payments and HTTP status.
     */
    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    /**
     * Endpoint to process a payment by its ID.
     *
     * @param id The ID of the payment to process.
     * @return ResponseEntity with the processed payment object and HTTP status.
     */
    @PutMapping("/{id}/process")
    public ResponseEntity<Payment> processPayment(@PathVariable Long id) {
        Payment processedPayment = paymentService.processPayment(id);
        if (processedPayment != null) {
            return new ResponseEntity<>(processedPayment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
