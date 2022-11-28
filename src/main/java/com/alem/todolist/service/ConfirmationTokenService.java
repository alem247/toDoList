package com.alem.todolist.service;

import com.alem.todolist.model.ConfirmationToken;
import org.springframework.stereotype.Service;

@Service
public interface ConfirmationTokenService {
    void saveConfirmationToken(ConfirmationToken token);
}
