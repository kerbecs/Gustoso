package com.project.restaurant.adapters.controller.rest;

import com.project.restaurant.adapters.exception.ErrorBody;
import com.project.restaurant.adapters.exception.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestControllerAdvice {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorBody> noSuchElementExceptionHandler(NoSuchElementException e, WebRequest webRequest) {
        ErrorBody errorBody = ErrorBody.builder()
                .path(webRequest.getDescription(false))
                .status(HttpStatus.NOT_FOUND.value())
                .timestamp(System.currentTimeMillis())
                .message(e.getMessage())
                .build();

        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> validationFailed(MethodArgumentNotValidException e) {
        Map<String, String> map = new HashMap<>();

        e.getBindingResult()
                .getAllErrors()
                .forEach(err -> {
                    String field = ((FieldError) err).getField();
                    String message = err.getDefaultMessage();

                    map.put(field, message);
                });

        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
}
