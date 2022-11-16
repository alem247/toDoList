package com.alem.todolist.service;


import com.alem.todolist.model.Task;
import com.alem.todolist.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<User> fetchAllUsers();
    User addUser(User x);
    String removeUser(long id);
    String updateUsername(long id, String newUserName);
    String updatePhonenum(long id, String newPhoneNum);
    String updateAddress(long id, String newAddress);

    List<Optional<Task>> fetchAllUserTasks(long id);
}
