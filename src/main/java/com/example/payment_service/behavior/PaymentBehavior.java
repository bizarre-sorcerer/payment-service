package com.example.payment_service.behavior;

import com.example.payment_service.enums.PaymentStatusEnum;
import com.example.payment_service.presentation.dto.request.PaymentCreateRequest;
import com.example.payment_service.presentation.dto.response.PaymentGetAllResponse;
import com.example.payment_service.presentation.dto.response.PaymentOperationResponse;
import com.example.payment_service.presentation.dto.response.PaymentGetResponse;

public interface PaymentBehavior {

    PaymentOperationResponse createPayment(PaymentCreateRequest paymentCreateRequest);

    PaymentGetResponse getPaymentById(Integer id);

    PaymentOperationResponse updatePaymentStatus(
            Integer id,
            PaymentStatusEnum newStatus
    );

    PaymentGetAllResponse getAllByClientId(Integer clientId);
}
