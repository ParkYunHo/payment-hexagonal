package com.john.payment.payment.adapters.out.persistence;

import java.util.Optional;

/**
 * @author john.09
 * @since 2022.12.20
 */
public interface TransactionRepositoryDsl {
    Optional<TransactionEntity> findByIdWithLimit(String mngNo, int size);
}
