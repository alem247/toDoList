package com.alem.todolist;
import com.alem.todolist.model.Task;
import com.alem.todolist.model.User;
import com.alem.todolist.repository.ToDoListRepository;
import com.alem.todolist.repository.UserRepository;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    private UserService userService = new UserServiceImpl(userRepository, toDoListRepository);

    @BeforeEach
    void setup(){
        ArrayList<Integer> alemtasks = new ArrayList<>();
        ArrayList<Integer> benjtasks = new ArrayList<>();
        alemtasks.add(1);
        alemtasks.add(2);
        alemtasks.add(3);
        benjtasks.add(3);
        benjtasks.add(4);
        benjtasks.add(5);
        MockitoAnnotations.openMocks(this);
        User Alem = new User(1, "Alem", "Čaušević", "alem247", "Badjurova ulica 3",
                "070314180", alemtasks);
        User Benjamin = new User(2, "Benjamin", "Midzic", "benjobenjo", "kod a faze",
                "062699996", benjtasks);
        List<User> users = Arrays.asList(Alem, Benjamin);
        when(userRepository.findAll()).thenReturn(users);
        userRepository.save(Alem);
        userRepository.save(Benjamin);
    }

    @Test
    void shouldFetchAllUsers(){
        List<User> users = userRepository.findAll();
        when(userService.fetchAllUsers()).thenReturn(users);
        assertEquals(users.size(), userService.fetchAllUsers().size());
    }

    @Test
    void shouldAddUser(){
        ArrayList<Integer> isaktasks = new ArrayList<>();
        isaktasks.add(5);
        isaktasks.add(6);
        isaktasks.add(7);
        User Isak = new User(3, "Isak Tarik", "Delic", "tarikaznjensi", "kod fileka",
                "061932443", isaktasks);
        when(userService.addUser(Isak)).thenReturn(Isak);
        assertEquals(Isak, userService.addUser(Isak));
    }

    @Test
    void shouldRemoveUser(){
        userService.removeUser(1);
        String expectedReturnStatement = "User successfully removed.";
        assertEquals(expectedReturnStatement , userService.removeUser(1));
    }

    @Test
    void shouldUpdateUsername(){
        String newUsername = "test55";
        userService.updateUsername(1, newUsername);
        String expectedReturnStatement =
                "User: " + userRepository.findById(1).getName() + "has a new username: " + newUsername;
        assertEquals(expectedReturnStatement, userService.updateUsername(1, newUsername));
    }

    @Test
    void shouldUpdatePhonenum(){
        String newPhoneNumber = "062049980";
        userService.updatePhonenum(1, "062049980");
        String expectedReturnStatement = "User: " + userRepository.findById(1).getName()  + "has a new phone number: " +
                newPhoneNumber;
        assertEquals(expectedReturnStatement, userService.updatePhonenum(1, newPhoneNumber));
    }

    @Test
    void shouldUpdateAddress(){
        String newAddress = "Gerbiceva 53";
        userService.updateAddress(1, newAddress);
        String expectedReturnStatement = "User: " + userRepository.findById(1).getName()  + "has a new address: " +
                newAddress;
        assertEquals(expectedReturnStatement, userService.updateAddress(1, newAddress));
    }

    @Test
    void shouldFetchAllUserTasks(){
        List<Optional<Task>> AlemTasks = userService.fetchAllUserTasks(1);
        when(userService.fetchAllUserTasks(1)).thenReturn(AlemTasks);
        assertEquals(AlemTasks, userService.fetchAllUserTasks(1));
    }


}
