package com.fullstackbd.student.exception.handler;

import com.fullstackbd.student.model.constants.Status;
import com.fullstackbd.student.model.dto.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ValidationExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<ValidationError> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, List<String>> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            fieldErrors.computeIfAbsent(error.getField(), k -> new LinkedList<>()).add(error.getDefaultMessage());
        });
        ValidationError errorResponse = ValidationError
                .builder()
                .status(Status.ERROR)
                .message("Validation Failed")
                .errors(fieldErrors)
                .build();
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
