package com.alem.todolist.service;


import com.alem.todolist.model.Task;
import com.alem.todolist.model.TaskDto;
import com.alem.todolist.model.User;
import com.alem.todolist.model.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<UserDto> fetchAllUsers();

    UserDto fetchUser(long id);
    UserDto addUser(User x);
    String removeUser(long id);
    UserDto updateUsername(long id, String newUserName);
    UserDto updatePhoneNumber(long id, String newPhoneNum);
    UserDto updateAddress(long id, String newAddress);

    List<TaskDto> fetchAllUserTasks(long id);
}
