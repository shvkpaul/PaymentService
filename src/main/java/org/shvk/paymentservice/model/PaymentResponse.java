package org.shvk.paymentservice.model;

import java.time.Instant;

public record PaymentResponse(
        long paymentId,
        String status,
        PaymentMode paymentMode,
        long amount,
        Instant paymentDate,
        long orderId
) {
}
