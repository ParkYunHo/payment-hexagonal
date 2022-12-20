package com.john.payment.payment.adapters.out.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * @author john.09
 * @since 2022.12.20
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class TransactionRepositoryImpl implements TransactionRepositoryDsl {
    private final JPAQueryFactory jpaQuery;

    @Override
    public Optional<TransactionEntity> findByIdWithLimit(String mngNo, int size) {
        return Optional.ofNullable(
            jpaQuery
                .selectFrom(QTransactionEntity.transactionEntity)
                .where(QTransactionEntity.transactionEntity.mngNo.eq(mngNo))
                .limit(size)
                .fetchFirst()
        );
    }
}
