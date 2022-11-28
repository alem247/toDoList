package com.alem.todolist.service;

import com.alem.todolist.model.ConfirmationToken;
import com.alem.todolist.repository.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    public ConfirmationTokenServiceImpl(ConfirmationTokenRepository confirmationTokenRepository){
        this.confirmationTokenRepository = confirmationTokenRepository;
    }
    @Override
    public void saveConfirmationToken(ConfirmationToken token) {
        this.confirmationTokenRepository.save(token);
    }
}
