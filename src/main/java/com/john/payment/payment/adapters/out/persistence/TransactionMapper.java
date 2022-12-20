package com.john.payment.payment.adapters.out.persistence;

import com.john.payment.payment.domain.Transaction;
import org.springframework.stereotype.Component;

/**
 * @author john.09
 * @since 2022.12.20
 */
@Component
public class TransactionMapper {

    public Transaction toDomain(TransactionEntity entity) {
        return Transaction.builder()
            .mngNo(entity.getMngNo())
            .status(entity.getStatus())
            .price(entity.getPrice())
            .vat(entity.getVat())
            .installMonths(entity.getInstallMonths())
            .cardInfo(entity.getCardInfo())
            .build();
    }

    public TransactionEntity toEntity(Transaction domain) {
        return TransactionEntity.builder()
            .mngNo(domain.getMngNo())
            .status(domain.getStatus())
            .price(domain.getPrice())
            .vat(domain.getVat())
            .installMonths(domain.getInstallMonths())
            .cardInfo(domain.getCardInfo())
            .build();
    }
}
