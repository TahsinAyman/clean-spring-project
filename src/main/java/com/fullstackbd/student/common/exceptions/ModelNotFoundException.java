package com.fullstackbd.student.common.exceptions;
public class ModelNotFoundException extends DatabaseException {
    public ModelNotFoundException(String message) {
        super(message);
    }

    public ModelNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}