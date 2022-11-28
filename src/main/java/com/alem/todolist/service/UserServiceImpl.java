package com.alem.todolist.service;

import com.alem.todolist.model.Task;
import com.alem.todolist.model.TaskDto;
import com.alem.todolist.model.User;
import com.alem.todolist.model.UserDto;
import com.alem.todolist.myMethods;
import com.alem.todolist.repository.ToDoListRepository;
import com.alem.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;


@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private ToDoListRepository toDoListRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, ToDoListRepository toDoListRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.toDoListRepository = toDoListRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<UserDto> fetchAllUsers() {
        return myMethods.convertListToUserDtos(this.userRepository.findAll());
    }

    @Override
    public UserDto fetchUser(long id) {
        return new UserDto(this.userRepository.findById(id));
    }

    @Override
    public String signUpUser(User user) {
        boolean userExists = userRepository.findByEmail(user.getEmail()) == user;
        if (userExists){
            throw new IllegalStateException("Email already taken");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        // todo : send confirmation token
        userRepository.save(user);
        return "it works";
    }

    @Override
    public List<TaskDto> fetchUserTasks(String username) {
        return myMethods.convertListToTaskDtos
                (this.userRepository.getTasksForUser(this.userRepository.findByUsername(username).getId())
                        .stream()
                        .map(id -> this.toDoListRepository.findById(Long.valueOf(id)).get())
                        .collect(Collectors.toList()));
    }

    @Override
    public String removeUser(long id) {
        this.userRepository.delete(this.userRepository.findById(id));
        return "User successfully removed.";
    }

    @Override
    public UserDto updateUsername(long id, String newUserName) {
        User user = this.userRepository.findById(id);
        user.setUsername(newUserName);
        return new UserDto(user);
    }

    @Override
    public UserDto updatePhoneNumber(long id, String newPhoneNumber) {
        User user = this.userRepository.findById(id);
        user.setPhone_num(newPhoneNumber);
        return new UserDto(user);
    }

    @Override
    public UserDto updateAddress(long id, String newAddress) {
        User user = this.userRepository.findById(id);
        user.setAddress(newAddress);
        return new UserDto(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username);
    }
}
