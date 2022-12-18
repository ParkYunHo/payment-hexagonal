package com.john.payment.payment.application.service;

import com.john.payment.payment.application.port.in.CancelUsecase;
import com.john.payment.payment.application.port.in.InquiryUseCase;
import com.john.payment.payment.application.port.in.PaymentUseCase;
import com.john.payment.payment.domain.payment.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author john.09
 * @since 2022.12.18
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService implements PaymentUseCase, CancelUsecase, InquiryUseCase {

    private final PaymentRepository paymentRepository;

    @Override
    public InquiryDto inquiryAccount(String mngId) {
        var inquiry = paymentRepository.findById(mngId).orElseGet(null);
        return new InquiryDto(
            inquiry.getMngNo(),
            inquiry.getStatus(),
            inquiry.getPrice(),
            inquiry.getVat(),
            inquiry.getInstallMonths(),
            inquiry.getCardInfo()
        );
    }
}