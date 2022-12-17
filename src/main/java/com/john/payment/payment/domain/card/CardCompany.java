package com.john.payment.payment.domain.card;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CARD_COMPANY")
public class CardCompany {
    @Id
    @Column(name = "TRANSACTION_ID")
    private String transactionId;
}
