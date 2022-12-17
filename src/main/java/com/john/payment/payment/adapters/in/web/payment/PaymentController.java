package com.john.payment.payment.adapters.in.web.payment;

import com.john.payment.common.BaseController;
import com.john.payment.payment.application.port.in.CancelUsecase;
import com.john.payment.payment.application.port.in.InquiryUseCase;
import com.john.payment.payment.application.port.in.PaymentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentController extends BaseController {
    private final PaymentUseCase paymentUseCase;
    private final CancelUsecase cancelUsecase;
    private final InquiryUseCase inquiryUseCase;

//    @GetMapping("/pay")
//    public
}
