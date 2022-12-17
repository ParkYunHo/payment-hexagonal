package com.john.payment.payment.application.service;

import com.john.payment.payment.application.port.in.CancelUsecase;
import com.john.payment.payment.application.port.in.InquiryUseCase;
import com.john.payment.payment.application.port.in.PaymentUseCase;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements PaymentUseCase, CancelUsecase, InquiryUseCase {

}
