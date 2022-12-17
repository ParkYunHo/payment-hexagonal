package com.john.payment.domain.card;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CARD_COMPANY")
public class CardCompany {
    @Id
    @Column(name = "TRANSACTION_ID", columnDefinition = "트랜잭션 ID")
    private String transactionId;
}
