package com.project.restaurant.adapters.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class MvcControllerAdvice {
    @ExceptionHandler(Exception.class)
    public String unknownException(Exception e){
        return "error";
    }
}
