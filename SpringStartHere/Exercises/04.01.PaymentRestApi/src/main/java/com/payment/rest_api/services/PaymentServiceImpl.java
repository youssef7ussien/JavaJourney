package com.payment.rest_api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.payment.rest_api.models.Payment;
import com.payment.rest_api.repositories.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment processPayment(Long id) {
        // For simplicity, let's assume processing a payment just changes its status
        Payment payment = paymentRepository.findById(id);
        if (payment != null) {
            payment.setStatus("Processed");
            return paymentRepository.save(payment);
        }
        return null;
    }
}
