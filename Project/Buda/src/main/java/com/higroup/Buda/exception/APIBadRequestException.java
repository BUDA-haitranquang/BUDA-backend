package com.higroup.Buda.exception;

public class APIBadRequestException extends RuntimeException{

    public APIBadRequestException(String message) {
        super(message);
    }

    public APIBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
