package org.shvk.paymentservice.service;

import org.shvk.paymentservice.model.PaymentRequest;
import org.shvk.paymentservice.model.PaymentResponse;

public interface PaymentService {

    Long doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailsByOrderId(long orderId);
}
