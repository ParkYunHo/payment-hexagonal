package com.john.payment.payment.domain.payment;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * @author john.09
 * @since 2022.12.19
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepositoryDsl {

    private final JPAQueryFactory jpaQuery;

    @Override
    public Optional<Payment> findByIdWithLimit(String mngNo, int size) {
        return Optional.ofNullable(
            jpaQuery
                .selectFrom(QPayment.payment)
                .where(QPayment.payment.mngNo.eq(mngNo))
                .limit(size)
                .fetchFirst()
        );
    }
}
