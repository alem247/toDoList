package com.alem.todolist.service;

import com.alem.todolist.model.Task;
import com.alem.todolist.model.User;
import com.alem.todolist.repository.ToDoListRepository;
import com.alem.todolist.repository.UserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
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
    public User fetchUser(long id) {
        return this.userRepository.findById(id);
    }

    @Override
    public User addUser(User x) {
        return this.userRepository.save(x);
    }

    @Override
    public String removeUser(long id) {
        this.userRepository.delete(this.userRepository.findById(id));
        return "User successfully removed.";
    }

    @Override
    public User updateUsername(long id, String newUserName) {
        this.userRepository.findById(id).setUsername(newUserName);
        return this.userRepository.findById(id);
    }

    @Override
    public User updatePhoneNumber(long id, String newPhoneNumber) {
        this.userRepository.findById(id).setPhone_num(newPhoneNumber);
        return this.userRepository.findById(id);
    }

    @Override
    public User updateAddress(long id, String newAddress) {
         this.userRepository.findById(id).setAddress(newAddress);
        return this.userRepository.findById(id);
    }

    @Override
    public List<Optional<Task>> fetchAllUserTasks(long id) {
         User temp = userRepository.findById(id);
         List<Optional<Task>> userTasks = new ArrayList<>();
         int k = temp.getUser_Tasks().length;
         for (int i = 0; i < k; i++){
             long taskId = temp.getUser_Tasks()[i];
             userTasks.add(toDoListRepository.findById((taskId)));
         }
         return userTasks;
    }
}
