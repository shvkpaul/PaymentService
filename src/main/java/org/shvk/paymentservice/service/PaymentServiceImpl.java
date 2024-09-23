package org.shvk.paymentservice.service;

import lombok.extern.log4j.Log4j2;
import org.shvk.paymentservice.entity.TransactionDetails;
import org.shvk.paymentservice.model.PaymentMode;
import org.shvk.paymentservice.model.PaymentRequest;
import org.shvk.paymentservice.model.PaymentResponse;
import org.shvk.paymentservice.repository.TransactionDetailsRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService {

    private TransactionDetailsRepository transactionDetailsRepository;

    public PaymentServiceImpl(TransactionDetailsRepository transactionDetailsRepository) {
        this.transactionDetailsRepository = transactionDetailsRepository;
    }

    @Override
    public Long doPayment(PaymentRequest paymentRequest) {
        log.info("Recording payment details: {}", paymentRequest);

        TransactionDetails transactionDetails
                = TransactionDetails.builder()
                .orderId(paymentRequest.orderId())
                .paymentMode(paymentRequest.paymentMode().name())
                .referenceNumber(paymentRequest.referenceNumber())
                .paymentDate(Instant.now())
                .paymentStatus("SUCCESS")
                .amount(paymentRequest.amount())
                .build();

        transactionDetailsRepository.save(transactionDetails);

        log.info("Transaction complete with id: {}", transactionDetails.getId());

        return transactionDetails.getId();
    }

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(long orderId) {
        log.info("Getting payment details for orderId: {}", orderId);

        TransactionDetails transactionDetails = transactionDetailsRepository.findByOrderId(orderId);

        PaymentResponse paymentResponse
                = new PaymentResponse(
                transactionDetails.getId(),
                transactionDetails.getPaymentStatus(),
                PaymentMode.valueOf(transactionDetails.getPaymentMode()),
                transactionDetails.getAmount(),
                transactionDetails.getPaymentDate(),
                transactionDetails.getOrderId()
        );

        return paymentResponse;
    }
}
