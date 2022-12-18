package com.john.payment.payment.domain.payment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author john.09
 * @since 2022.12.18
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "PAYMENT_TRANSACTION")
public class Payment {
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
