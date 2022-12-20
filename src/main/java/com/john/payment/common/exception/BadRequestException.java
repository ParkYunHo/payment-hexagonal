package com.john.payment.common.exception;


/**
 * @author john.09
 * @since 2022.12.18
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException() { super(); }
    public BadRequestException(String msg) { super(msg); }
    public BadRequestException(Throwable e) { super(e); }
    public BadRequestException(String msg, Throwable e) { super(msg, e); }
}
