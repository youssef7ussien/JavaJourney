package com.payment.rest_api.services;

import java.util.List;

import com.payment.rest_api.models.Payment;

public interface PaymentService {

    /**
     * Create a new payment.
     *
     * @param payment The payment object to be created.
     * @return The created payment object.
     */
    Payment createPayment(Payment payment);

    /**
     * Retrieve a payment by its ID.
     *
     * @param id The ID of the payment to retrieve.
     * @return The payment with the given ID, or null if not found.
     */
    Payment getPaymentById(Long id);

    /**
     * Retrieve all payments.
     *
     * @return A list of all payments.
     */
    List<Payment> getAllPayments();

    /**
     * Process a payment by its ID.
     *
     * @param id The ID of the payment to process.
     * @return The processed payment.
     */
    Payment processPayment(Long id);

}
