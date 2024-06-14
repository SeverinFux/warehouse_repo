package com.axa.warehouse.exceptionHandler;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String msg){
        super(String.format(msg));
    }
}
