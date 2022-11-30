package com.alem.todolist;
import com.alem.todolist.model.ConfirmationToken;
import com.alem.todolist.model.User;
import com.alem.todolist.model.UserDto;
import com.alem.todolist.model.UserRole;
import com.alem.todolist.repository.ToDoListRepository;
import com.alem.todolist.repository.UserRepository;
import com.alem.todolist.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class UserServiceTests {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ToDoListRepository toDoListRepository;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @MockBean
    private ConfirmationTokenService confirmationTokenService;

    @InjectMocks
    private UserService userService = new UserServiceImpl(
            userRepository, toDoListRepository, bCryptPasswordEncoder, confirmationTokenService);


    @BeforeEach
    void setup(){
        User Alem = new User("Alem", "Causevic", "alem247", "alem@gmail.com", "alem123", "Badjurova 3",
                "062048890", UserRole.USER);
        User Benjamin = new User("Benjamin", "Midzic", "benjamin", "benjamin@gmail.com", "benjamin123", "Poljanska 1", "062669996", UserRole.USER);
        List<User> users = Arrays.asList(Alem, Benjamin);
        when(userRepository.findAll()).thenReturn(users);
        userRepository.save(Alem);
        userRepository.save(Benjamin);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFetchAllUsers(){
        List<UserDto> users = myMethods.convertListToUserDtos(userRepository.findAll());
        when(userService.fetchAllUsers()).thenReturn(users);
        assertEquals(users, userService.fetchAllUsers());
    }

    @Test
    void shouldRemoveUser(){
        User Alem = new User("Alem", "Causevic", "alem247", "alem@gmail.com", "alem123", "Badjurova 3",
                "062048890", UserRole.USER);
        UserDto testAlem = new UserDto();
        testAlem.setUser(Alem);
        when(userService.fetchUser(any(Long.class))).thenReturn(testAlem);
        userService.removeUser(Alem.getId());
        String expectedReturnStatement = "User successfully removed.";
        assertEquals(expectedReturnStatement , userService.removeUser(Alem.getId()));
    }

    @Test
    void shouldUpdateUsername(){
        User Alem = new User("Alem", "Causevic", "alem247", "alem@gmail.com", "alem123", "Badjurova 3",
                "062048890", UserRole.USER);
        String newUsername = "test55";
        when(userService.fetchUser(any(Long.class))).thenReturn(new UserDto(Alem));
        userService.updateUsername(Alem.getId(), newUsername);
        assertEquals(new UserDto(Alem), userService.updateUsername(Alem.getId(), newUsername));
    }

    @Test
    void shouldUpdatePhoneNumber(){
        User Alem = new User("Alem", "Causevic", "alem247", "alem@gmail.com", "alem123", "Badjurova 3",
                "062048890", UserRole.USER);
        UserDto testAlem = new UserDto(Alem);
        when(userService.fetchUser(any(Long.class))).thenReturn(testAlem);
        String newPhoneNumber = "062049980";
        userService.updatePhoneNumber(Alem.getId(), "062049980");
        assertEquals(Alem, userService.updatePhoneNumber(Alem.getId(), newPhoneNumber));
    }

    @Test
    void shouldUpdateAddress(){
        User Alem = new User("Alem", "Causevic", "alem247", "alem@gmail.com", "alem123", "Badjurova 3",
                "062048890", UserRole.USER);
        UserDto testAlem = new UserDto(Alem);
        when(userService.fetchUser(anyLong())).thenReturn(testAlem);
        String newAddress = "Gerbiceva 53";
        userService.updateAddress(Alem.getId(), newAddress);
        assertEquals(testAlem, userService.updateAddress(Alem.getId(), newAddress));
    }
    @Test
    void shouldSignUpUser(){
        User Alem = new User("Alem", "Causevic", "alem247", "alem@gmail.com", "alem123", "Badjurova 3",
                "062048890", UserRole.USER);
        String encodedPassword = bCryptPasswordEncoder.encode(Alem.getPassword());
        Alem.setPassword(encodedPassword);
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(10),
                Alem
        );
        this.confirmationTokenService.saveConfirmationToken(confirmationToken);
        this.userRepository.save(Alem);
        when(userService.signUpUser(any(User.class))).thenReturn(token);
        assertEquals(token, userService.signUpUser(Alem));

    }

}
