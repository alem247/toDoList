package com.alem.todolist.service;

import com.alem.todolist.model.ConfirmationToken;
import com.alem.todolist.repository.ConfirmationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    public ConfirmationTokenServiceImpl(ConfirmationTokenRepository confirmationTokenRepository){
        this.confirmationTokenRepository = confirmationTokenRepository;
    }


    @Override
    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmationTime(token, LocalDateTime.now());
    }

    @Override
    public ConfirmationToken getToken(String token) {
        return this.confirmationTokenRepository.findByToken(token);
    }

    @Override
    public long saveConfirmationToken(ConfirmationToken token) {
        this.confirmationTokenRepository.save(token);
        return token.getId();
    }
}
