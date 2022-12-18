package com.john.payment.payment.adapters.in.web.payment;

import com.john.payment.common.BaseController;
import com.john.payment.common.dto.BaseResponse;
import com.john.payment.payment.adapters.in.web.payment.dto.InquiryInput;
import com.john.payment.payment.application.port.in.CancelUsecase;
import com.john.payment.payment.application.port.in.InquiryUseCase;
import com.john.payment.payment.application.port.in.PaymentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author john.09
 * @since 2022.12.18
 */
@RestController
@RequiredArgsConstructor
public class PaymentController extends BaseController {
    private final PaymentUseCase paymentUseCase;
    private final CancelUsecase cancelUsecase;
    private final InquiryUseCase inquiryUseCase;

    @GetMapping("/pay")
    public BaseResponse inquiry(InquiryInput input) {
        var result = inquiryUseCase.inquiryAccount(input.mngId());
        return new BaseResponse().success(result);
    }

    @PostMapping("/pay")
    public BaseResponse payment() {
        return new BaseResponse().success(null);
    }

    @PutMapping("/pay")
    public BaseResponse cancel() {
        return new BaseResponse().success(null);
    }
}
