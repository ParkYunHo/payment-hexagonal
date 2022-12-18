package com.john.payment.common.dto;

import org.springframework.http.HttpStatus;

/**
 * @author john.09
 * @since 2022.12.18
 */
public record BaseResponse(
    String message,
    HttpStatus status,
    Object data
) {
    public BaseResponse() {
        this("", null, null);
    }

    public BaseResponse(String message, HttpStatus status) {
        this(message, status, null);
    }

    public <T> BaseResponse success(T data) {
        return new BaseResponse("Success", HttpStatus.OK, data);
    }
    public BaseResponse successNoContent() {
        return new BaseResponse("Success", HttpStatus.OK, null);
    }
}
