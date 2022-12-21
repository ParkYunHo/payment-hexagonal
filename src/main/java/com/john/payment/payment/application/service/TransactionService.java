package com.john.payment.payment.application.service;

import com.john.payment.common.constants.CommCode.Status;
import com.john.payment.common.exception.BadRequestException;
import com.john.payment.common.exception.NotFoundException;
import com.john.payment.common.utils.CipherUtils;
import com.john.payment.common.utils.FormatUtils;
import com.john.payment.payment.adapters.in.web.dto.PaymentInput;
import com.john.payment.payment.adapters.out.persistence.TransactionMapper;
import com.john.payment.payment.application.dto.CancelDto;
import com.john.payment.payment.application.dto.InquiryDto;
import com.john.payment.payment.application.dto.PaymentDto;
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
import org.springframework.transaction.annotation.Transactional;

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
    private final TransactionMapper mapper;

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
    public InquiryDto inquiry(String mngNo, int size) {
        try{
            var entity = inquiryPort.findTransactionEntity(mngNo, size);

            var cardInfo = CipherUtils.decode(entity.getCardInfo());
            var cardInfoMap = FormatUtils.setStringToCardInfo(cardInfo);
            var maskedCardNo = FormatUtils.masking(String.valueOf(cardInfoMap.get("cardNo")), 6, 3);
            var subMngNo = entity.getCancelMngNo().stream().map(x -> x.getMngNo()).toList();

            return InquiryDto.builder()
                .mngNo(entity.getMngNo())
                .cardNo(maskedCardNo)
                .expiryDate(cardInfoMap.get("expiryDate"))
                .cvc(cardInfoMap.get("cvc"))
                .status(entity.getStatus())
                .price(entity.getPrice())
                .vat(entity.getVat())
                .subMngNo(subMngNo)
                .build();
        } catch (NotFoundException ne) {
            throw ne;
        } catch (BadRequestException be) {
            throw be;
        } catch (Exception e){
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
    public PaymentDto payment(PaymentInput input) {
        try{
            String mngNo = UUID.randomUUID().toString();
            String cardInfo = FormatUtils.setCardInfoToString(String.valueOf(input.getCardNo()), input.getExpiryDate(), input.getCvc());

            Transaction transaction = Transaction.builder()
                .mngNo(mngNo)
                .status(Status.PAYMENT.getCode())
                .price(input.getPrice())
                .vat(input.getVat() == null ? Math.round(input.getPrice() / 11.0) : input.getVat())
                .installMonths(input.getInstallMonths())
                .cardInfo(CipherUtils.encode(cardInfo))
                .build();
            savePort.saveTransaction(transaction);

            return PaymentDto.builder()
                .mngNo(mngNo)
                .build();
        }catch (BadRequestException be) {
            throw be;
        }catch (Exception e) {
            throw new BadRequestException("");
        }
    }

    /**
     * 결제정보 취소
     *
     * @param payMngNo {@link String}
     * @param price {@link Long}
     * @param vat {@link Long}
     * @return result {@link Transaction}
     * @author john.09
     * @since 2022.12.20
     */
    @Override
    @Transactional
    public CancelDto cancel(String payMngNo, Long price, Long vat) {
        try{
            var payEntity = inquiryPort.findTransactionEntity(payMngNo, 1);
            String mngNo = UUID.randomUUID().toString();

            // 결제취소 트랜잭션 저장
            Transaction cancelTransaction = Transaction.builder()
                .mngNo(mngNo)
                .status(Status.CANCEL.getCode())
                .price(price)
                .vat(vat)
                .installMonths(0L)
                .cardInfo(payEntity.getCardInfo())
                .build();
            savePort.saveTransaction(cancelTransaction, payEntity);

            // 결제 트랜잭션 업데이트
            payEntity.setPrice(0L);
            payEntity.setVat(0L);
            savePort.saveTransaction(mapper.toDomain(payEntity));

            return CancelDto.builder()
                .mngNo(mngNo)
                .build();
        }catch (BadRequestException be) {
            throw be;
        }catch (Exception e){
            throw new BadRequestException();
        }
    }
}