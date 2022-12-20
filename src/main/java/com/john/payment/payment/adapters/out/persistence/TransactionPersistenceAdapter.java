package com.john.payment.payment.adapters.out.persistence;

import com.john.payment.common.exception.NotFoundException;
import com.john.payment.payment.application.port.out.InquiryPort;
import com.john.payment.payment.application.port.out.SavePort;
import com.john.payment.payment.domain.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author john.09
 * @since 2022.12.20
 */
@Repository
@RequiredArgsConstructor
public class TransactionPersistenceAdapter implements InquiryPort, SavePort {

    private final TransactionMapper mapper;
    private final TransactionRepository transactionRepository;
    private final TransactionRepositoryDsl transactionRepositoryDsl;

    /**
     * Transaction 조회
     *
     * @param mngNo {@link String}
     * @param size {@link Integer}
     * @return result {@link Transaction}
     * @author john.09
     * @since 2022.12.20
     */
    @Override
    public Transaction findTransaction(String mngNo, int size) {
        var entity = transactionRepositoryDsl.findByIdWithLimit(mngNo, size).orElseThrow(() ->
                new NotFoundException("결제정보를 조회할 수 없습니다.")
        );
        return mapper.toDomain(entity);
    }

    /**
     * Transaction 저장
     *
     * @param domain {@link Transaction}
     * @author john.09
     * @since 2022.12.20
     */
    @Override
    public void saveTransaction(Transaction domain) {
        var entity = mapper.toEntity(domain);
        transactionRepository.save(entity);
    }
}
