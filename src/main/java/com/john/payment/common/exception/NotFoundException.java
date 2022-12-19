package com.john.payment.common.exception;


/**
 * @author john.09
 * @since 2022.12.18
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String msg) { super(msg); }
    public NotFoundException(Throwable e) { super(e); }
    public NotFoundException(String msg, Throwable e) { super(msg, e); }
}
