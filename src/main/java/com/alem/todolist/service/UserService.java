package com.alem.todolist.service;


import com.alem.todolist.model.Task;
import com.alem.todolist.model.TaskDto;
import com.alem.todolist.model.User;
import com.alem.todolist.model.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService{

    List<UserDto> fetchAllUsers();
    UserDto fetchUser(long id);
    UserDto loadByUsername(String username);
    UserDto registerUser(long id, String name, String surname, String email, String password,
                         String address, String phone_number);
    String removeUser(long id);
    UserDto updateUsername(long id, String newUserName);
    UserDto updatePhoneNumber(long id, String newPhoneNum);
    UserDto updateAddress(long id, String newAddress);

    boolean authentication(String username, String password);

}
