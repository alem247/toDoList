package com.alem.todolist;
import com.alem.todolist.model.User;
import com.alem.todolist.model.UserDto;
import com.alem.todolist.repository.ToDoListRepository;
import com.alem.todolist.repository.UserRepository;
import com.alem.todolist.service.ToDoListService;
import com.alem.todolist.service.UserService;
import com.alem.todolist.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class UserServiceTests {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ToDoListRepository toDoListRepository;

    @InjectMocks
    private UserService userService = new UserServiceImpl(userRepository);

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        User Alem = new User(1, "Alem", "Čaušević", "alem247", "Badjurova ulica 3",
                "070314180", new int[]{1, 2, 3});
        User Benjamin = new User(2, "Benjamin", "Midzic", "benjobenjo", "kod a faze",
                "062699996", new int[]{3, 4, 5});
        List<User> users = Arrays.asList(Alem, Benjamin);
        when(userRepository.findAll()).thenReturn(users);
        userRepository.save(Alem);
        userRepository.save(Benjamin);
    }

    @Test
    void shouldFetchAllUsers(){
        List<UserDto> userDtos = myMethods.convertListToUserDtos(userRepository.findAll());
        when(userService.fetchAllUsers()).thenReturn(userDtos);
        assertEquals(userDtos, userService.fetchAllUsers());
    }

    @Test
    void shouldAddUser(){
        User Isak = new User(3, "Isak Tarik", "Delic", "tarikaznjensi", "kod fileka",
                "061932443", new int[]{5, 6, 7});
        UserDto testIsak = new UserDto(Isak);
        when(userService.addUser(Isak)).thenReturn(testIsak);
        assertEquals(testIsak, userService.addUser(Isak));
    }

    @Test
    void shouldRemoveUser(){
        User Isak = new User(3, "Isak Tarik", "Delic", "tarikaznjensi", "kod fileka",
                "061932443", new int[]{5, 6, 7});
        UserDto testIsak = new UserDto(Isak);
        when(userService.fetchUser(any(Long.class))).thenReturn(testIsak);
        userService.removeUser(3L);
        String expectedReturnStatement = "User successfully removed.";
        assertEquals(expectedReturnStatement , userService.removeUser(3L));
    }

    @Test
    void shouldUpdateUsername(){
        User Isak = new User(3, "Isak Tarik", "Delic", "tarikaznjensi", "kod fileka",
                "061932443", new int[]{5, 6, 7});
        String newUsername = "test55";
        when(userService.fetchUser(any(Long.class))).thenReturn(new UserDto(Isak));
        userService.updateUsername(3, newUsername);
        assertEquals(new UserDto(Isak), userService.updateUsername(3, newUsername));
    }

    @Test
    void shouldUpdatePhoneNumber(){
        User Isak = new User(3, "Isak Tarik", "Delic", "tarikaznjensi", "kod fileka",
                "061932443", new int[]{5, 6, 7});
        UserDto testIsak = new UserDto(Isak);
        when(userService.fetchUser(any(Long.class))).thenReturn(testIsak);
        String newPhoneNumber = "062049980";
        userService.updatePhoneNumber(3, "062049980");
        assertEquals(testIsak, userService.updatePhoneNumber(3, newPhoneNumber));
    }

    @Test
    void shouldUpdateAddress(){
        User Isak = new User(3, "Isak Tarik", "Delic", "tarikaznjensi", "kod fileka",
                "061932443", new int[]{5, 6, 7});
        UserDto testIsak = new UserDto(Isak);
        when(userService.fetchUser(any(Long.class))).thenReturn(testIsak);
        String newAddress = "Gerbiceva 53";
        userService.updateAddress(3, newAddress);
        assertEquals(testIsak, userService.updateAddress(3, newAddress));
    }

}
