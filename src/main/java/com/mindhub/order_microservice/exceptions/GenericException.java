package com.mindhub.order_microservice.exceptions;

public class GenericException extends RuntimeException {
    public GenericException(String message) {
        super(message);
    }
}
