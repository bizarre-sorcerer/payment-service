package com.example.payment_service.exceptions;

import com.example.payment_service.exceptions.base.AbstractException;
import com.example.payment_service.presentation.dto.response.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends AbstractException {

    public BadRequestException(ErrorDto error) {
        super(error);
    }

    public BadRequestException(List<ErrorDto> errors) {
        super(errors);
    }

    public BadRequestException() {}
}
