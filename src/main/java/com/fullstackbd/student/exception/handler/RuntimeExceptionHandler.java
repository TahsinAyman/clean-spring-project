package com.fullstackbd.student.exception.handler;

import com.fullstackbd.student.common.exceptions.DatabaseException;
import com.fullstackbd.student.model.dto.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RuntimeExceptionHandler {
    @ExceptionHandler(DatabaseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Message> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Message
                        .builder()
                        .message(ex.getMessage())
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .build()
        );
    }
}
