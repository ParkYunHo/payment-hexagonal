package com.john.payment.payment.adapters.in.web;

import com.john.payment.common.BaseController;
import com.john.payment.common.dto.BaseResponse;
import com.john.payment.payment.adapters.in.web.dto.CancelInput;
import com.john.payment.payment.adapters.in.web.dto.InquiryInput;
import com.john.payment.payment.adapters.in.web.dto.PaymentInput;
import com.john.payment.payment.application.port.in.CancelUsecase;
import com.john.payment.payment.application.port.in.InquiryUseCase;
import com.john.payment.payment.application.port.in.PaymentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    /**
     * 트랜잭션 조회
     *
     * @param input {@link InquiryInput}
     * @return result {@link BaseResponse}
     * @author john.09
     * @since 2022.12.20
     */
    @GetMapping("/pay")
    public BaseResponse inquiry(InquiryInput input) {
        var result = inquiryUseCase.inquiry(input.getMngNo(), input.getSize());
        return new BaseResponse().success(result);
    }

    /**
     * 결제정보 저장
     *
     * @param input {@link PaymentInput}
     * @return result {@link BaseResponse}
     * @author john.09
     * @since 2022.12.20
     */
    @PostMapping("/pay")
    public BaseResponse payment(@RequestBody PaymentInput input) {
        var result = paymentUseCase.payment(input);
        return new BaseResponse().success(result);
    }

    /**
     * 결제정보 취소
     *
     * @param input {@link CancelInput}
     * @return result {@link BaseResponse}
     * @author john.09
     * @since 2022.12.20
     */
    @PutMapping("/pay")
    public BaseResponse cancel(@RequestBody CancelInput input) {
        var result = cancelUsecase.cancel(input.getMngNo(), input.getPrice(), input.getVat());
        return new BaseResponse().success(result);
    }
}
