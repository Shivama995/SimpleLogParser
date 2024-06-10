package com.logs.parser.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new Object(){
        public final String Message = ex.getMessage();
        public final String Details = request.getDescription(false);
        public final String Type    = ex.getClass().getTypeName();
        }, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
