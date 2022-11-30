package com.alem.todolist.service;

import com.alem.todolist.model.RegistrationRequest;
import org.springframework.stereotype.Service;
@Service
public interface RegistrationService {

    String register(RegistrationRequest request);
    String confirmToken(String token);
}
