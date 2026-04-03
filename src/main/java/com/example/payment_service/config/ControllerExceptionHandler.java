package com.example.payment_service.config;

import com.example.payment_service.exceptions.BadRequestException;
import com.example.payment_service.exceptions.NotFoundException;
import com.example.payment_service.presentation.dto.response.ErrorDto;
import com.example.payment_service.presentation.dto.response.ErrorResponse;
import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.function.Function;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    private static final Function<ConstraintViolation<?>, String> M = s -> String.format(s.getMessageTemplate(), s.getInvalidValue());

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException ex) {
        return response(
                HttpStatus.BAD_REQUEST,
                ErrorResponse.of(ErrorDto.builder()
                        .code("400")
                        .message(ex.getMessage())
                        .build()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        List<ErrorDto> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> ErrorDto.builder()
                        .code("400")
                        .message("Bad request: " + fieldError.getField() + " " + fieldError.getDefaultMessage())
                        .build())
                .toList();

        return response(HttpStatus.BAD_REQUEST, ErrorResponse.ofErrors(errors));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException ex) {
        return response(
                HttpStatus.NOT_FOUND,
                ErrorResponse.of(ErrorDto.builder()
                        .code("404")
                        .message("Requested resourse not found")
                        .build()));
    }

    private ResponseEntity<ErrorResponse> response(HttpStatus status, ErrorResponse response) {
        return ResponseEntity.status(status)
                .headers(createHeaders())
                .body(response);
    }

    private static HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}