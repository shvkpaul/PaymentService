package org.shvk.paymentservice.model;

public record PaymentRequest(
        long orderId,
        long amount,
        String referenceNumber,
        PaymentMode paymentMode
) {
}
