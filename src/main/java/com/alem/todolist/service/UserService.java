package com.alem.todolist.service;


import com.alem.todolist.model.Task;
import com.alem.todolist.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    public List<User> fetchAllUsers();
    public User addUser(User x);
    public void removeUser(long id);
    public void updateUsername(long id, String newUserName);
    public void updatePhonenum(long id, String newPhoneNum);
    public void updateAddress(long id, String newAddress);

    public List<Optional<Task>> fetchAllUserTasks(long id);
}
