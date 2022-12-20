package com.john.payment.payment.application.service;

import com.john.payment.common.constants.CommCode.Status;
import com.john.payment.common.exception.BadRequestException;
import com.john.payment.common.utils.CipherUtils;
import com.john.payment.common.utils.FormatUtils;
import com.john.payment.payment.adapters.in.web.dto.PaymentInput;
import com.john.payment.payment.application.port.in.CancelUsecase;
import com.john.payment.payment.application.port.in.InquiryUseCase;
import com.john.payment.payment.application.port.in.PaymentUseCase;
import com.john.payment.payment.application.port.out.InquiryPort;
import com.john.payment.payment.application.port.out.SavePort;
import com.john.payment.payment.domain.Transaction;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author john.09
 * @since 2022.12.18
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService implements InquiryUseCase, PaymentUseCase, CancelUsecase {
    private final InquiryPort inquiryPort;
    private final SavePort savePort;

    /**
     * 트랜잭션 조회
     *
     * @param mngNo {@link String}
     * @param size {@link Integer}
     * @return result {@link Transaction}
     * @author john.09
     * @since 2022.12.20
     */
    @Override
    public Transaction inquiry(String mngNo, int size) {
        var transaction = inquiryPort.findTransaction(mngNo, size);

        try{
            var cardInfo = CipherUtils.decode(transaction.getCardInfo());
            var cardInfoMap = FormatUtils.setStringToCardInfo(cardInfo);
            var maskedCardNo = FormatUtils.masking(String.valueOf(cardInfoMap.get("cardNo")), 6, 3);

            return transaction;
        }catch (BadRequestException be) {
            throw be;
        }catch (Exception e){
            throw new BadRequestException();
        }
    }

    /**
     * 결제정보 저장
     *
     * @param input {@link PaymentInput}
     * @return result {@link Transaction}
     * @author john.09
     * @since 2022.12.20
     */
    @Override
    public Transaction payment(PaymentInput input) {
        try{
            String mngNo = UUID.randomUUID().toString();
            String cardInfo = FormatUtils.setCardInfoToString(input.getCardNo(), input.getExpiryDate(), input.getCvc());

            Transaction transaction = Transaction.builder()
                .mngNo(mngNo)
                .status(Status.PAYMENT.getCode())
                .price(input.getPrice())
                .vat(input.getVat() == null ? Math.round(input.getPrice() / 11.0) : input.getVat())
                .installMonths(input.getInstallMonths())
                .cardInfo(CipherUtils.encode(cardInfo))
                .build();
            savePort.saveTransaction(transaction);

            return transaction;
        }catch (BadRequestException be) {
            throw be;
        }catch (Exception e) {
            throw new BadRequestException("");
        }
    }

    /**
     * 결제정보 취소
     *
     * @param mngNo {@link String}
     * @param price {@link Long}
     * @param vat {@link Long}
     * @return result {@link Transaction}
     * @author john.09
     * @since 2022.12.20
     */
    @Override
    public Transaction cancel(String mngNo, Long price, Long vat) {
        var transaction = inquiryPort.findTransaction(mngNo, 1);

        try{
            Transaction cancelTransaction = Transaction.builder()
                .mngNo(mngNo)
                .status(Status.CANCEL.getCode())
                .price(transaction.getPrice())
                .vat(transaction.getVat())
                .installMonths(0L)
                .cardInfo(transaction.getCardInfo())
                .build();
            savePort.saveTransaction(cancelTransaction);

            return transaction;
        }catch (BadRequestException be) {
            throw be;
        }catch (Exception e){
            throw new BadRequestException();
        }
    }
}