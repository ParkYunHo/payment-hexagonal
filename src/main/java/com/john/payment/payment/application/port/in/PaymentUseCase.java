package com.john.payment.payment.application.port.in;

import com.john.payment.payment.adapters.in.web.dto.PaymentInput;
import com.john.payment.payment.application.dto.PaymentDto;

/**
 * @author john.09
 * @since 2022.12.18
 */
public interface PaymentUseCase {
    PaymentDto payment(PaymentInput input);
}
