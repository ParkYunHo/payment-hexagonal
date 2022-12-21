package com.john.payment.payment.adapters.out.persistence;

import com.john.payment.payment.domain.Transaction;
import org.springframework.stereotype.Component;

/**
 * @author john.09
 * @since 2022.12.20
 */
@Component
public class TransactionMapper {

    /**
     * Entity에서 Domain으로 변환
     *
     * @param entity {@link TransactionEntity}
     * @return domain {@link Transaction}
     */
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

    /**
     * Domain에서 Entity로 변환
     *
     * @param domain {@link Transaction}
     * @return entity {@link TransactionEntity}
     */
    public TransactionEntity toEntity(Transaction domain) {
        return this.toEntity(domain, null);
    }

    /**
     * Domain에서 Entity로 변환
     *
     * @param domain {@link Transaction}
     * @param payEntity {@link TransactionEntity}
     * @return entity {@link TransactionEntity}
     */
    public TransactionEntity toEntity(Transaction domain, TransactionEntity payEntity) {
        return TransactionEntity.builder()
            .mngNo(domain.getMngNo())
            .status(domain.getStatus())
            .price(domain.getPrice())
            .vat(domain.getVat())
            .installMonths(domain.getInstallMonths())
            .cardInfo(domain.getCardInfo())
            .payMngNo(payEntity)
            .build();
    }
}
