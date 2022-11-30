package com.alem.todolist.service;

import com.alem.todolist.model.ConfirmationToken;
import com.alem.todolist.model.RegistrationRequest;
import com.alem.todolist.model.User;
import com.alem.todolist.model.UserRole;
import com.alem.todolist.security.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final EmailValidator emailValidator;
    private final UserService userService;

    private final ConfirmationTokenService confirmationTokenService;

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

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = this.confirmationTokenService.getToken(token);

        if (confirmationToken.getConfirmationTime() != null) {
            throw new IllegalStateException("email already confirmed");
        }
        LocalDateTime expiredAt = confirmationToken.getExpirationTime();
        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }
        confirmationTokenService.setConfirmedAt(token);
        this.userService.enableUser(
                confirmationToken.getUser().getEmail());
        return "confirmed";
    }


}
