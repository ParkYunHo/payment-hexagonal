package com.john.payment.domain.payment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PAYMENT_TRANSACTION")
public class Payment {
    @Id
    @Column(name = "MNG_NO", nullable = false, columnDefinition = "관리번호")
    private String mngNo;

    @Column(name = "STATUS", columnDefinition = "결제 상태")
    private String status;

    @Column(name = "PRICE", columnDefinition = "트랜잭션 금액")
    private Long price;

    @Column(name = "VAT", columnDefinition = "부가가치세")
    private Long vat;

    @Column(name = "INSTALL_MONTHS", columnDefinition = "할부개월수")
    private Long installMonths;

    @Column(name = "CARD_INFO", columnDefinition = "카드정보")
    private String cardInfo;
}
