package com.john.payment.payment.application.port.out;

import com.john.payment.payment.application.port.in.InquiryUseCase.InquiryDto;
import com.john.payment.payment.domain.payment.Payment;

/**
 * @author john.09
 * @since 2022.12.18
 */
public interface InquiryPort {
    Payment inquiryAccount();
}
