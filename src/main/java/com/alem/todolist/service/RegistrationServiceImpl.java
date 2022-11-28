package com.alem.todolist.service;

import com.alem.todolist.model.RegistrationRequest;
import com.alem.todolist.model.User;
import com.alem.todolist.model.UserRole;
import com.alem.todolist.security.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final EmailValidator emailValidator;
    private final UserService userService;

    @Override
    public String register(RegistrationRequest request) {
        boolean isValid = emailValidator.test(request.getEmail());
        if (!isValid){
            throw new IllegalStateException("email not valid");
        }
        return userService.signUpUser(
                new User(
                        request.getName(),
                        request.getSurname(),
                        request.getUsername(),
                        request.getEmail(),
                        request.getPassword(),
                        request.getAddress(),
                        request.getPhoneNumber(),
                        UserRole.USER)
        );
    }


}
