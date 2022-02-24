package com.maxel.decrypto.services.exceptions;

public class ObjectAlreadyExistException extends RuntimeException {

    public ObjectAlreadyExistException(String message) {
        super(message);
    }

    public ObjectAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
