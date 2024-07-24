package com.payment.rest_api.repositories;

import java.util.List;

import com.payment.rest_api.models.Payment;

public interface PaymentRepository {

    /**
     * Save a new payment.
     *
     * @param payment The payment to be saved.
     * @return The saved payment.
     */
    Payment save(Payment payment);

    /**
     * Retrieve a payment by its ID.
     *
     * @param id The ID of the payment to retrieve.
     * @return The payment with the given ID, or null if not found.
     */
    Payment findById(Long id);

    /**
     * Retrieve all payments.
     *
     * @return A list of all payments.
     */
    List<Payment> findAll();

    /**
     * Update the status of a payment.
     *
     * @param id     The ID of the payment to update.
     * @param status The new status of the payment.
     * @return The updated payment, or null if the payment was not found.
     */
    Payment updateStatus(Long id, String status);

    /**
     * Delete a payment by its ID.
     *
     * @param id The ID of the payment to delete.
     * @return True if the payment was deleted successfully, false otherwise.
     */
    boolean deleteById(Long id);
}
