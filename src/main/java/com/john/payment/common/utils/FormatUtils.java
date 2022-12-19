package com.john.payment.common.utils;

import com.john.payment.common.exception.BadRequestException;
import java.util.Map;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;

/**
 * @author john.09
 * @since 2022.12.19
 */
@Slf4j
public class FormatUtils {

    public static String setCardInfoToString(Long cardNo, Long expiryDate, Long cvc) {
        return new StringBuffer()
            .append(cardNo).append(":")
            .append(expiryDate).append(":")
            .append(cvc)
            .toString();
    }

    public static Map<String, Long> setStringToCardInfo(String cardInfo) {
        var splitCardInfo = cardInfo.split(":");
        return Map.of(
            "cardNo", Long.parseLong(splitCardInfo[0]),
            "expiryDate", Long.parseLong(splitCardInfo[1]),
            "cvc", Long.parseLong(splitCardInfo[2])
        );
    }

    public static String masking(String value, int start, int size) {
        if(value.length() < (start + size)) {
            return value;
        }

        // index 설정
        start--;
        //

        StringBuffer sb = new StringBuffer(value);
        IntStream.rangeClosed(start, (start + size)-1)
                .forEach(i -> sb.setCharAt(i, '*'));

        return sb.toString();
    }
}
