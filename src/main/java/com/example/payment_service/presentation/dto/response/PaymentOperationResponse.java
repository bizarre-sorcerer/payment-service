package com.example.payment_service.presentation.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentOperationResponse {

    public String paymentId;

    public String status;
}
