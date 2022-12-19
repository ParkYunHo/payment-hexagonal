package com.john.payment.common.constants;

/**
 * @author john.09
 * @since 2022.12.19
 */
public class CommCode {

    public enum Status {
        PAYMENT("payment"),
        CANCEL("cancel");

        String code;

        Status(String code) {
            this.code = code;
        }

        public String getCode() {
            return this.code;
        }
    }
}
