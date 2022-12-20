package com.john.payment.payment.application.service;

import com.john.payment.common.constants.CommCode.Status;
import com.john.payment.common.exception.BadRequestException;
import com.john.payment.common.exception.NotFoundException;
import com.john.payment.common.utils.CipherUtils;
import com.john.payment.common.utils.FormatUtils;
import com.john.payment.payment.adapters.in.web.payment.dto.PaymentInput;
import com.john.payment.payment.application.port.in.CancelUsecase;
import com.john.payment.payment.application.port.in.InquiryUseCase;
import com.john.payment.payment.application.port.in.PaymentUseCase;
import com.john.payment.payment.domain.payment.Payment;
import com.john.payment.payment.domain.payment.PaymentRepository;
import com.john.payment.payment.domain.payment.PaymentRepositoryDsl;
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
public class PaymentService implements PaymentUseCase, CancelUsecase, InquiryUseCase {

    private final PaymentRepository paymentRepository;
    private final PaymentRepositoryDsl paymentRepositoryDsl;

    /**
     * 트랜잭션 조회
     *
     * @param mngNo {@link String}
     * @param size {@link Integer}
     * @return result {@link InquiryDto}
     * @author john.09
     * @since 2022.12.20
     */
    @Override
    public InquiryDto inquiry(String mngNo, int size) {
        var inquiry = paymentRepositoryDsl.findByIdWithLimit(mngNo, size).orElseThrow(() ->
            new NotFoundException("결제정보를 조회할 수 없습니다.")
        );

        try{
            var cardInfo = CipherUtils.decode(inquiry.getCardInfo());
            var cardInfoMap = FormatUtils.setStringToCardInfo(cardInfo);
            var maskedCardNo = FormatUtils.masking(String.valueOf(cardInfoMap.get("cardNo")), 6, 3);

            return new InquiryDto(
                inquiry.getMngNo(),
                inquiry.getStatus(),
                inquiry.getPrice(),
                inquiry.getVat(),
                maskedCardNo,
                cardInfoMap.get("expiryDate"),
                cardInfoMap.get("cvc")
            );
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
     * @return result {@link PaymentDto}
     * @author john.09
     * @since 2022.12.20
     */
    @Override
    public PaymentDto payment(PaymentInput input) {
        try{
            String mngNo = UUID.randomUUID().toString();
            String cardInfo = FormatUtils.setCardInfoToString(input.getCardNo(), input.getExpiryDate(), input.getCvc());

            Payment payment = new Payment();
            payment.setMngNo(mngNo);
            payment.setStatus(Status.PAYMENT.getCode());
            payment.setPrice(input.getPrice());
            payment.setVat(input.getVat() == null ? Math.round(input.getPrice() / 11.0) : input.getVat());
            payment.setInstallMonths(input.getInstallMonths());
            payment.setCardInfo(CipherUtils.encode(cardInfo));
            paymentRepository.save(payment);

            return new PaymentDto(mngNo, "");
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
     * @return result {@link CancelDto}
     * @author john.09
     * @since 2022.12.20
     */
    @Override
    public CancelDto cancel(String mngNo, Long price, Long vat) {
        var inquiry = paymentRepository.findById(mngNo).orElseThrow(() ->
            new NotFoundException("결제정보를 조회할 수 없습니다.")
        );

        try{
            Payment payment = new Payment();
            payment.setMngNo(mngNo);
            payment.setStatus(Status.CANCEL.getCode());
            payment.setPrice(inquiry.getPrice());
            payment.setVat(inquiry.getVat());
            payment.setInstallMonths(0L);
            payment.setCardInfo(inquiry.getCardInfo());
            paymentRepository.save(payment);

            return new CancelDto(inquiry.getMngNo(), "");
        }catch (BadRequestException be) {
            throw be;
        }catch (Exception e){
            throw new BadRequestException();
        }
    }
}