package com.alem.todolist;

import com.alem.todolist.controller.ToDoListController;
import com.alem.todolist.model.Task;
import com.alem.todolist.model.User;
import com.alem.todolist.repository.ToDoListRepository;
import com.alem.todolist.repository.UserRepository;
import com.alem.todolist.service.UserService;
import com.alem.todolist.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @MockBean
    UserRepository userRepository;

    @MockBean
    ToDoListRepository toDoListRepository;

    @InjectMocks
    private UserService userService = new UserServiceImpl(userRepository, toDoListRepository);

    @BeforeEach
    void setup(){
        User Alem = new User(1, "Alem", "Čaušević", "alem247", "Badjurova ulica 3",
                "070314180", new int[]{1, 2, 3});
        User Benjamin = new User(2, "Benjamin", "Midzic", "benjobenjo", "kod a faze",
                "062699996", new int[]{3, 4, 5});
        List<User> users = Arrays.asList(Alem, Benjamin);
        when(userRepository.findAll()).thenReturn(users);
        userService.addUser(Alem);
        userService.addUser(Benjamin);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldAddUser(){
        User Isak = new User(3, "Isak Tarik", "Delic", "tarikaznjensi", "kod fileka",
                "061932443", new int[]{5, 6, 7});
        when(userService.addUser(Isak)).thenReturn(Isak);
        assertEquals(Isak, userService.addUser(Isak));
    }

    @Test
    void shouldRemoveUser(){
        userService.removeUser(1);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldUpdateUsername(){
        userService.updateUsername(2, "benjaminmidzic");
        verify(userRepository, times(1)).findbyId(2L);
    }

    @Test
    void shouldUpdatePhonenum(){
        userService.updatePhonenum(1, "062049980");
        verify(userRepository, times(1)).findbyId(1L);
    }

    @Test
    void shouldUpdateAddress(){
        userService.updateAddress(2, "kod ase");
        verify(userRepository, times(1)).findbyId(2L);
    }

    @Test
    void shouldFetchAllUserTasks(){
        List<Optional<Task>> AlemTasks = userService.fetchAllUserTasks(1);
        when(userService.fetchAllUserTasks(1)).thenReturn(AlemTasks);
        assertEquals(3, userService.fetchAllUserTasks(1).size());
    }


}
