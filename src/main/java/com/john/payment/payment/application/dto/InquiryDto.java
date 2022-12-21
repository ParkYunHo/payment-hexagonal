package com.john.payment.payment.application.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

/**
 * @author john.09
 * @since 2022.12.21
 */
@Getter
@Builder
public class InquiryDto {
    private String mngNo;
    private String cardNo;
    private Long expiryDate;
    private Long cvc;
    private String status;
    private Long price;
    private Long vat;

    private List<String> subMngNo;
}
