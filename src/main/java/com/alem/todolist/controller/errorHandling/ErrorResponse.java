package com.alem.todolist.controller.errorHandling;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.Instant;


@Data
public class ErrorResponse {
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Instant timestamp;
    private HttpStatus status;

    ErrorResponse(String message, HttpStatus status){
        this.message = message;
        this.status = status;
        this.timestamp = Instant.now();
    }


}
