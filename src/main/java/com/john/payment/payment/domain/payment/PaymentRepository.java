package com.john.payment.payment.domain.payment;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author john.09
 * @since 2022.12.19
 */
public interface PaymentRepository extends JpaRepository<Payment, String> {

}
