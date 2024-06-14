package com.axa.warehouse.exceptionHandler;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String msg){
        super(String.format(msg));
    }
}
