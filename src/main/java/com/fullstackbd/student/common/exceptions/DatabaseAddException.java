package com.fullstackbd.student.common.exceptions;
public class DatabaseAddException extends DatabaseException {
    public DatabaseAddException(String message) {
        super(message);
    }

    public DatabaseAddException(String message, Throwable cause) {
        super(message, cause);
    }
}
