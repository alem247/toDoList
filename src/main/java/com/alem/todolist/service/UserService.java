package com.alem.todolist.service;


import com.alem.todolist.model.Task;
import com.alem.todolist.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<User> fetchAllUsers();

    User fetchUser(long id);
    User addUser(User x);
    String removeUser(long id);
    User updateUsername(long id, String newUserName);
    User updatePhoneNumber(long id, String newPhoneNum);
    User updateAddress(long id, String newAddress);

    List<Optional<Task>> fetchAllUserTasks(long id);
}
