package com.john.payment.payment.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @implNote DDD의 도메인은 pojo방식으로 구성, 도메인명은 동사로 명명해야되지만 우선 Transaction으로 명칭함
 *
 * @author john.09
 * @since 2022.12.18
 */
@NoArgsConstructor
public class Transaction {
    @Getter
    private String mngNo;
    @Getter
    private String status;
    @Getter
    private Long price;
    @Getter
    private Long vat;
    @Getter
    private Long installMonths;
    @Getter
    private String cardInfo;

    @Builder
    public Transaction(String mngNo, String status, Long price, Long vat, Long installMonths, String cardInfo) {
        this.mngNo = mngNo;
        this.status = status;
        this.price = price;
        this.vat = vat;
        this.installMonths = installMonths;
        this.cardInfo = cardInfo;
    }
}
