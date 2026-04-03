package com.example.payment_service.presentation.dto.response;

import com.example.payment_service.enums.CurrencyEnum;
import com.example.payment_service.enums.PaymentStatusEnum;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PaymentGetResponse {

    private Integer paymentId;
    private BigDecimal amount;
    private CurrencyEnum currency;
    private PaymentStatusEnum status;
    private String description;
    private Integer clientId;
}
