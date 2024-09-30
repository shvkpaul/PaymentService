package org.shvk.paymentservice.controller;

import org.shvk.paymentservice.model.PaymentRequest;
import org.shvk.paymentservice.model.PaymentResponse;
import org.shvk.paymentservice.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Long> doPayment(
            @RequestBody PaymentRequest paymentRequest
    ) {
        return new ResponseEntity<>(
                paymentService.doPayment(paymentRequest),
                HttpStatus.OK
        );
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentResponse> getPaymentDetailsByOrderId(
            @PathVariable long orderId) {
        PaymentResponse paymentResponse = paymentService.getPaymentDetailsByOrderId(orderId);
        return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
    }
}
