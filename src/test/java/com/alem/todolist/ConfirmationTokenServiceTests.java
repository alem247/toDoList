package com.alem.todolist;


import com.alem.todolist.model.ConfirmationToken;
import com.alem.todolist.model.User;
import com.alem.todolist.model.UserRole;
import com.alem.todolist.repository.ConfirmationTokenRepository;
import com.alem.todolist.service.ConfirmationTokenService;
import com.alem.todolist.service.ConfirmationTokenServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ConfirmationTokenServiceTests {

    /*  void saveConfirmationToken(ConfirmationToken token);

    int setConfirmedAt(String token);

    ConfirmationToken getToken(String token);
    String confirmToken(String token);
*/

    @MockBean
    private ConfirmationTokenRepository confirmationTokenRepository;

    @InjectMocks
    private ConfirmationTokenService confirmationTokenService = new ConfirmationTokenServiceImpl(confirmationTokenRepository);

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveConfirmationToken(){
        User Alem = new User("Alem", "Causevic", "alem247", "alem@gmail.com", "alem123", "Badjurova 3",
                "062048890", UserRole.USER);
        ConfirmationToken token = new ConfirmationToken(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                Alem
        );
        long tokenId = 1;
        token.setId(tokenId);
        when(confirmationTokenService.saveConfirmationToken(any(ConfirmationToken.class))).thenReturn(tokenId);
        assertEquals(tokenId, confirmationTokenService.saveConfirmationToken(token));
    }
    // TODO:
    @Test
    void shouldSetConfirmedAt(){

    }

    // TODO:
    @Test
    void shouldGetToken(){

    }

}
