package com.john.payment.common.handler;

import com.john.payment.common.dto.BaseResponse;
import com.john.payment.common.exception.BadRequestException;
import com.john.payment.common.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author john.09
 * @since 2022.12.18
 */
@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class PaymentExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BaseResponse badRequestException(HttpServletRequest request, BadRequestException e) {
        return new BaseResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BaseResponse notFoundException(HttpServletRequest request, NotFoundException e) {
        return new BaseResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
