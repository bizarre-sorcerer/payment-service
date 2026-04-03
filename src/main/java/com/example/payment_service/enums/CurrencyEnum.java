package com.example.payment_service.enums;

import com.example.payment_service.exceptions.BadRequestException;
import com.example.payment_service.presentation.dto.response.ErrorDto;

public enum CurrencyEnum {
    KZT,
    USD,
    EUR,
    RUB,
    CNY;

    public static CurrencyEnum from(String value) {
        try {
            return CurrencyEnum.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(ErrorDto.builder().code("400").message("Bad request: invalid currency").build());
        }
    }
}
