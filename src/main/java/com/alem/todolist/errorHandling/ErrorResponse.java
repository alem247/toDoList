package com.alem.todolist.errorHandling;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import java.time.Instant;


@Data
public class ErrorResponse {

    private String message;
    private Instant timestamp;
    private HttpStatus status;

    ErrorResponse(String message, HttpStatus status){
        this.message = message;
        this.status = status;
        this.timestamp = Instant.now();
    }


}
