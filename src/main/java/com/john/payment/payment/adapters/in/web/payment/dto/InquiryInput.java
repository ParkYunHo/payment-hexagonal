package com.john.payment.payment.adapters.in.web.payment.dto;

import com.john.payment.common.exception.BadRequestException;
import lombok.NonNull;

public record InquiryInput(
    String mngId
) {
    public InquiryInput {
        if (mngId == null || mngId.isEmpty()) {
            throw new BadRequestException("관리번호가 누락되었습니다.");
        }
    }
}
