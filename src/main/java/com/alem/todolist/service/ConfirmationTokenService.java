package com.alem.todolist.service;

import com.alem.todolist.model.ConfirmationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface ConfirmationTokenService {
    long saveConfirmationToken(ConfirmationToken token);

    int setConfirmedAt(String token);

    ConfirmationToken getToken(String token);;

}
