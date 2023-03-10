package com.john.payment.payment.application.port.in;

import com.john.payment.payment.application.dto.CancelDto;

/**
 * @author john.09
 * @since 2022.12.18
 */
public interface CancelUsecase {
    CancelDto cancel(String mngNo, Long price, Long vat);
}
