package com.example.payment_service.presentation.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class PaymentCreateRequest {

    @NotNull
    @Min(value = 1, message = "must be greater than 0")
    public BigDecimal amount;

    @NotNull
    public String currency;

    @NotNull
    public String description;

    @NotNull
    public Integer clientId;
}
