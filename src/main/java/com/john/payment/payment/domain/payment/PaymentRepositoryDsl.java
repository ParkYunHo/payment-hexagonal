package com.john.payment.payment.domain.payment;

import java.util.Optional;

/**
 * @author john.09
 * @since 2022.12.19
 */
public interface PaymentRepositoryDsl {
    public Optional<Payment> findByIdWithLimit(String mngNo, int size);
}
