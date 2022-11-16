package com.alem.todolist.service;

import com.alem.todolist.model.Task;
import com.alem.todolist.model.User;
import com.alem.todolist.repository.ToDoListRepository;
import com.alem.todolist.repository.UserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.yaml.snakeyaml.events.Event;

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
    public String removeUser(long id) {
        this.userRepository.delete(this.userRepository.findById(id).get());
        return "User successfully removed.";
    }

    @Override
    public String updateUsername(long id, String newUserName) {
        this.userRepository.findById(id).get().setUsername(newUserName);
        return "User: " + userRepository.findById(id).get().getName() + "has a new username: " + newUserName;
    }

    @Override
    public String updatePhonenum(long id, String newPhoneNumber) {
        this.userRepository.findById(id).get().setPhonenum(newPhoneNumber);
        return "User: " + userRepository.findById(id).get().getName() + "has a new phone number: " + newPhoneNumber;
    }

    @Override
    public String updateAddress(long id, String newAddress) {
         this.userRepository.findById(id).get().setAddress(newAddress);
        return "User: " + userRepository.findById(id).get().getName() + "has a new address: " + newAddress;
    }

    @Override
    public List<Optional<Task>> fetchAllUserTasks(long id) {
         User temp = userRepository.findById(id).get();
         List<Optional<Task>> userTasks = new ArrayList<>();
         int k = temp.getUserTasks().length;
         for (int i = 0; i < k; i++){
             long taskId = temp.getUserTasks()[i];
             userTasks.add(toDoListRepository.findById((taskId)));
         }
         return userTasks;
    }
}
