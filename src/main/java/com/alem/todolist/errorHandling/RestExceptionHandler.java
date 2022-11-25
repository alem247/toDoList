package com.alem.todolist.errorHandling;


import com.alem.todolist.exceptions.InvalidGroupException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse){
        return new ResponseEntity<Object>(errorResponse, errorResponse.getStatus());
    }

    @ExceptionHandler(InvalidGroupException.class)
    public ResponseEntity<Object> handleInvalidGroupException(InvalidGroupException ex){
        String errorMessage = "Invalid group. Pick 1 of 3 : work, study, personal. \n" + ex.getMessage();
        ErrorResponse response = new ErrorResponse(errorMessage, HttpStatus.I_AM_A_TEAPOT);
        return buildResponseEntity(response);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex){
        String errorMessage = "No element for given value. Try fetching an existing one. \n" + ex.getMessage();
        ErrorResponse response = new ErrorResponse(errorMessage, HttpStatus.BAD_REQUEST);
        return buildResponseEntity(response);
    }
}
