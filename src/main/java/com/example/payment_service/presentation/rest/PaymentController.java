package com.example.payment_service.presentation.rest;

import com.example.payment_service.behavior.PaymentBehavior;
import com.example.payment_service.enums.PaymentStatusEnum;
import com.example.payment_service.presentation.dto.request.PaymentCreateRequest;
import com.example.payment_service.presentation.dto.response.PaymentOperationResponse;
import com.example.payment_service.presentation.dto.response.PaymentGetResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/payments")
public class PaymentController {

    private final PaymentBehavior paymentService;

    @PostMapping
    public ResponseEntity<PaymentOperationResponse> createNewPayment(
            @Valid @RequestBody PaymentCreateRequest request
    ) {
        log.info("createNewPayment |START| request: {}", request);
        var response = ResponseEntity.ok(paymentService.createPayment(request));

        log.info("createNewPayment |FINISH| result: {}", response);
        return response;
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentGetResponse> getPaymentStatus(@PathVariable Integer paymentId) {
        log.info("getPaymentStatust |START| paymentId: {}", paymentId);
        var response = ResponseEntity.ok(paymentService.getPaymentById(paymentId));

        log.info("getPaymentStatus |FINISH| result: {}", response);
        return response;
    }

    @PostMapping("/{paymentId}/confirm")
    public ResponseEntity<PaymentOperationResponse> confirmPayment(@PathVariable Integer paymentId) {
        log.info("confirmPayment |START| paymentId: {}", paymentId);
        var response = ResponseEntity.ok(paymentService.updatePaymentStatus(paymentId, PaymentStatusEnum.CONFIRMED));

        log.info("confirmPayment |FINISH| result: {}", response);
        return response;
    }

    @PostMapping("/{paymentId}/cancel")
    public ResponseEntity<PaymentOperationResponse> cancelPayment(@PathVariable Integer paymentId) {
        log.info("cancelPayment |START| paymentId: {}", paymentId);
        var response = ResponseEntity.ok(paymentService.updatePaymentStatus(paymentId, PaymentStatusEnum.CANCELED));

        log.info("cancelPayment |FINISH| result: {}", response);
        return response;
    }
}
