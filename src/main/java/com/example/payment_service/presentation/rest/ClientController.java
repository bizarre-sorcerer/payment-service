package com.example.payment_service.presentation.rest;

import com.example.payment_service.behavior.PaymentBehavior;
import com.example.payment_service.presentation.dto.response.PaymentGetAllResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/clients")
public class ClientController {

    private final PaymentBehavior paymentService;

    @GetMapping("/{clientId}/payments")
    public ResponseEntity<PaymentGetAllResponse> getAllPaymentsByClientId(@PathVariable Integer clientId) {
        log.info("getAllPaymentsByClientId |START| paymentId: {}", clientId);
        var response = ResponseEntity.ok(paymentService.getAllByClientId(clientId));

        log.info("getAllPaymentsByClientId |FINISH| result: {}", response);
        return response;
    }
}
