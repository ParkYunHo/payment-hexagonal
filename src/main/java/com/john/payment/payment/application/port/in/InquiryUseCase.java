package com.john.payment.payment.application.port.in;

/**
 * @author john.09
 * @since 2022.12.18
 */
public interface InquiryUseCase {
    InquiryDto inquiryAccount(String mngId);

    record InquiryDto(
        String mngNo,
        String status,
        Long price,
        Long vat,
        Long installMonths,
        String cardInfo
    ) { }
}
