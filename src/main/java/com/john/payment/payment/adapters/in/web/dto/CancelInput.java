package com.john.payment.payment.adapters.in.web.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author john.09
 * @since 2022.12.19
 */
@Getter
@Setter
public class CancelInput {
    private String mngNo;
    private Long price;
    private Long vat;
}
