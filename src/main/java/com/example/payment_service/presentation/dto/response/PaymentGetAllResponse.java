package com.example.payment_service.presentation.dto.response;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class PaymentGetAllResponse {

    private List<PaymentGetResponse> payments;
}
