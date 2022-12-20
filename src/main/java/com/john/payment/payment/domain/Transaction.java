package com.john.payment.payment.domain;


import com.john.payment.payment.adapters.in.web.dto.PaymentInput;
import lombok.Builder;

/**
 * @implNote DDD의 도메인은 pojo방식으로 구성
 *
 * @author john.09
 * @since 2022.12.18
 */
public class Transaction {
    private String mngNo;
    private String status;
    private Long price;
    private Long vat;
    private Long installMonths;
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

    // Getter
    public String getMngNo() {
        return mngNo;
    }
    public String getStatus() {
        return status;
    }
    public Long getPrice() {
        return price;
    }
    public Long getVat() {
        return vat;
    }
    public Long getInstallMonths() {
        return installMonths;
    }
    public String getCardInfo() {
        return cardInfo;
    }

//    // Behavior
//    public void inquiry(String mngNo, int size) {
//
//    }
//
//    public void payment(PaymentInput input) {
//
//    }
//
//    public void cancel(String mngNo, Long price, Long vat) {
//
//    }
}
