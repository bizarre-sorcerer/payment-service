package com.example.payment_service.service;

import com.example.payment_service.behavior.PaymentBehavior;
import com.example.payment_service.entities.PaymentEntity;
import com.example.payment_service.enums.CurrencyEnum;
import com.example.payment_service.enums.PaymentStatusEnum;
import com.example.payment_service.exceptions.BadRequestException;
import com.example.payment_service.exceptions.NotFoundException;
import com.example.payment_service.presentation.dto.request.PaymentCreateRequest;
import com.example.payment_service.presentation.dto.response.ErrorDto;
import com.example.payment_service.presentation.dto.response.PaymentGetAllResponse;
import com.example.payment_service.presentation.dto.response.PaymentOperationResponse;
import com.example.payment_service.presentation.dto.response.PaymentGetResponse;
import com.example.payment_service.repositories.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class PaymentService implements PaymentBehavior {

    private final PaymentRepository repo;

    @Override
    @Transactional
    public PaymentOperationResponse createPayment(PaymentCreateRequest request) {
        log.info("createPayment |START| clientId: {} amount: {}", request.clientId, request.amount);

        PaymentEntity payment = PaymentEntity.builder()
                .amount(request.amount)
                .currency(CurrencyEnum.from(request.currency))
                .description(request.description)
                .clientId(request.clientId)
                .status(PaymentStatusEnum.PENDING)
                .build();

        PaymentEntity savedPayment = repo.save(payment);
        log.info("createPayment |FINISH| clientId: {}", request.clientId);

        return PaymentOperationResponse.builder()
                .paymentId(String.valueOf(savedPayment.getId()))
                .status(String.valueOf(savedPayment.getStatus()))
                .build();
    }

    @Override
    @Transactional
    public PaymentGetResponse getPaymentById(Integer id) {
        log.info("getPaymentById |START| id: {}", id);

        var paymentOpt = repo.findById(id);
        if (paymentOpt.isPresent()){
            PaymentEntity payment = paymentOpt.get();

            log.info("getPaymentById |FINISH| id: {}", id);
            return PaymentGetResponse.builder()
                    .paymentId(payment.getId())
                    .amount(payment.getAmount())
                    .currency(payment.getCurrency())
                    .description(payment.getDescription())
                    .clientId(payment.getClientId())
                    .status(payment.getStatus())
                    .build();

        } else {
            throw new NotFoundException();
        }
    }

    @Override
    @Transactional
    public PaymentOperationResponse updatePaymentStatus(
            Integer id,
            PaymentStatusEnum newStatus
    ) {
        log.info("updatePaymentStatus |START| id: {}", id);

        var paymentOpt = repo.findById(id);
        if (paymentOpt.isPresent()){
            PaymentEntity payment = paymentOpt.get();

            if (payment.getStatus() == newStatus) {
                throw new BadRequestException(ErrorDto.builder().code("400").message("Payment status is already: " + payment.getStatus()).build());
            }

            payment.setStatus(newStatus);
            repo.save(payment);

            log.info("updatePaymentStatus |FINISH| id: {}", id);
            return PaymentOperationResponse.builder()
                    .paymentId(String.valueOf(payment.getId()))
                    .status(String.valueOf(payment.getStatus()))
                    .build();
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public PaymentGetAllResponse getAllByClientId(Integer clientId) {
        log.info("getAllByClientId |START| clientId: {}", clientId);

        List<PaymentEntity> payments = repo.getAllByClientId(clientId);

        // это очень странно. Зачем мне потенциальному атакеру сливать лишнюю инфу? Лучше просто пустой список возвращать
        // гавно код, но зато по тз)
        if (payments.isEmpty()) {
            throw new NotFoundException(ErrorDto.builder().code("404").message("Client not found").build());
        }

        List<PaymentGetResponse> responseList = payments.stream()
                .map(payment -> PaymentGetResponse.builder()
                        .paymentId(payment.getId())
                        .amount(payment.getAmount())
                        .currency(payment.getCurrency())
                        .description(payment.getDescription())
                        .clientId(payment.getClientId())
                        .status(payment.getStatus())
                        .build()
                )
                .toList();

        log.info("getAllByClientId |FINISH| clientId: {}", clientId);

        return PaymentGetAllResponse.builder()
                .payments(responseList)
                .build();
    }
}