package com.john.payment.payment.application.port.in;

import com.john.payment.payment.adapters.in.web.payment.dto.PaymentInput;

/**
 * @author john.09
 * @since 2022.12.18
 */
public interface PaymentUseCase {
    PaymentDto paymentAccount(PaymentInput input);

    record PaymentDto(
        String mngNo,
        String transactionId
    ) {}
}
