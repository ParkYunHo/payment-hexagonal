package com.john.payment.payment.application.port.out;


import com.john.payment.payment.adapters.out.persistence.TransactionEntity;
import com.john.payment.payment.domain.Transaction;

/**
 * @author john.09
 * @since 2022.12.18
 */
public interface SavePort {
    void saveTransaction(Transaction domain);
    void saveTransaction(Transaction domain, TransactionEntity payEntity);
}
