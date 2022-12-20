package com.john.payment.payment.adapters.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author john.09
 * @since 2022.12.20
 */
public interface TransactionRepository extends JpaRepository<TransactionEntity, String> {

}
