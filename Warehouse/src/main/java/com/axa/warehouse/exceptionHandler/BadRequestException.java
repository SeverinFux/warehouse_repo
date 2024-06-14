package com.axa.warehouse.exceptionHandler;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String msg){
        super(String.format(msg));
    }
}
