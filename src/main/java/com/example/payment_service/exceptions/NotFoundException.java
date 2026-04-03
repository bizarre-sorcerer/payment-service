package com.example.payment_service.exceptions;

import com.example.payment_service.exceptions.base.AbstractException;
import com.example.payment_service.presentation.dto.response.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends AbstractException {

    public NotFoundException(ErrorDto error) {
        super(error);
    }

    public NotFoundException(List<ErrorDto> errors) {
        super(errors);
    }

    public NotFoundException() {}
}

