package com.john.payment.common.utils;

import java.util.Map;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;

/**
 * @author john.09
 * @since 2022.12.19
 */
@Slf4j
public class FormatUtils {

    /**
     * 카드객체를 문자열로 변환
     *
     * @param cardNo {@link Long}
     * @param expiryDate {@link Long}
     * @param cvc {@link Long}
     * @return result {@link String}
     * @author yoonho
     * @since 2022.12.20
     */
    public static String setCardInfoToString(String cardNo, Long expiryDate, Long cvc) {
        return new StringBuffer()
            .append(cardNo).append(":")
            .append(expiryDate).append(":")
            .append(cvc)
            .toString();
    }

    /**
     * 문자열을 카드객체로 변환
     *
     * @param cardInfo {@link String}
     * @return result {@link Map}
     * @author yoonho
     * @since 2022.12.20
     */
    public static Map<String, Long> setStringToCardInfo(String cardInfo) {
        var splitCardInfo = cardInfo.split(":");
        return Map.of(
            "cardNo", Long.parseLong(splitCardInfo[0]),
            "expiryDate", Long.parseLong(splitCardInfo[1]),
            "cvc", Long.parseLong(splitCardInfo[2])
        );
    }

    /**
     * 카드문자열정보 마스킹처리
     *
     * @param value {@link String}
     * @param start {@link Integer}
     * @param size {@link Integer}
     * @return result {@link String}
     * @author yoonho
     * @since 2022.12.20
     */
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
