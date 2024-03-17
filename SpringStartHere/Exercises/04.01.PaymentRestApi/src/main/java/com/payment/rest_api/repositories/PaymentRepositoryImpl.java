package com.payment.rest_api.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.payment.rest_api.models.Payment;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {

    // Simulating an in-memory database
    private final Map<Long, Payment> paymentMap = new HashMap<>();
    private Long idSequence = 1L;

    @Override
    public Payment save(Payment payment) {
        // Assigning an ID to the payment if it's new
        if (payment.getId() == null) {
            payment.setId(idSequence++);
        }
        // Saving the payment in the map
        paymentMap.put(payment.getId(), payment);
        return payment;
    }

    @Override
    public Payment findById(Long id) {
        return paymentMap.get(id);
    }

    @Override
    public List<Payment> findAll() {
        return new ArrayList<>(paymentMap.values());
    }

    @Override
    public Payment updateStatus(Long id, String status) {
        Payment payment = paymentMap.get(id);
        if (payment != null) {
            payment.setStatus(status);
        }
        return payment;
    }

    @Override
    public boolean deleteById(Long id) {
        return paymentMap.remove(id) != null;
    }
}

