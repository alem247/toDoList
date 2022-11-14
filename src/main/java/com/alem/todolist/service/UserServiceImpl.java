package com.alem.todolist.service;

import com.alem.todolist.model.Task;
import com.alem.todolist.model.User;
import com.alem.todolist.repository.ToDoListRepository;
import com.alem.todolist.repository.UserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private ToDoListRepository toDoListRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ToDoListRepository toDoListRepository){
        this.userRepository = userRepository;
        this.toDoListRepository = toDoListRepository;
    }

    @Override
    public List<User> fetchAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User addUser(User x) {
        return this.userRepository.save(x);
    }

    @Override
    public void removeUser(long id) {
        this.userRepository.delete(this.userRepository.findbyId(id));
    }

    @Override
    public void updateUsername(long id, String newUserName) {
        this.userRepository.findbyId(id).setUsername(newUserName);
    }

    @Override
    public void updatePhonenum(long id, String newPhoneNumber) {
        this.userRepository.findbyId(id).setPhonenum(newPhoneNumber);
    }

    @Override
    public void updateAddress(long id, String newAddress) {
         this.userRepository.findbyId(id).setAddress(newAddress);
    }

    @Override
    public List<Optional<Task>> fetchAllUserTasks(long id) {
         User temp = userRepository.findbyId(id);
         List<Optional<Task>> userTasks = new ArrayList<>();
         for (int i = 0; i < temp.getUserTasks().length; i++){
             int taskId = temp.getUserTasks()[i]; // get task id from userTasks column
             userTasks.add(toDoListRepository.findById((long) taskId));
         }
         return userTasks;
    }
}
