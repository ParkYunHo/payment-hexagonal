package com.john.payment.payment.application.port.out;


import com.john.payment.payment.adapters.out.persistence.TransactionEntity;
import com.john.payment.payment.domain.Transaction;

/**
 * @author john.09
 * @since 2022.12.18
 */
public interface InquiryPort {
    Transaction findTransaction(String mngNo, int size);
    TransactionEntity findTransactionEntity(String mngNo, int size);
}
