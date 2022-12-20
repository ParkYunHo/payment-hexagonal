package com.john.payment.payment.adapters.in.web.payment.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author john.09
 * @since 2022.12.19
 */
@Getter
@Setter
public class PaymentInput {
    private Long cardNo;
    private Long expiryDate;
    private Long cvc;
    private Long installMonths;
    private Long price;
    private Long vat;
}