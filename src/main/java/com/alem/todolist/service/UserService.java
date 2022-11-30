package com.alem.todolist.service;


import com.alem.todolist.model.TaskDto;
import com.alem.todolist.model.User;
import com.alem.todolist.model.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {

    List<UserDto> fetchAllUsers();
    UserDto fetchUser(long id);
    String removeUser(long id);
    UserDto updateUsername(long id, String newUserName);
    UserDto updatePhoneNumber(long id, String newPhoneNum);
    UserDto updateAddress(long id, String newAddress);
    List<TaskDto> fetchUserTasks(String username);
    String signUpUser(User user);

    void enableUser(String email);
}
