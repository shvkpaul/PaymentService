package org.shvk.paymentservice.service;

import org.shvk.paymentservice.model.PaymentRequest;

public interface PaymentService {
    Long doPayment(PaymentRequest paymentRequest);
}
