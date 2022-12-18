package com.john.payment.payment.domain.card;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author john.09
 * @since 2022.12.18
 */
@Entity
@Table(name = "CARD_COMPANY")
public class CardCompany {
    @Id
    @Column(name = "TRANSACTION_ID")
    private String transactionId;
}
