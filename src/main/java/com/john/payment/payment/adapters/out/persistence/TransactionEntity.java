package com.john.payment.payment.adapters.out.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author john.09
 * @since 2022.12.20
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "PAYMENT_TRANSACTION")
public class TransactionEntity {
    @Id
    @Column(name = "MNG_NO", nullable = false)
    private String mngNo;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "PRICE")
    private Long price;

    @Column(name = "VAT")
    private Long vat;

    @Column(name = "INSTALL_MONTHS")
    private Long installMonths;

    @Column(name = "CARD_INFO")
    private String cardInfo;
}
