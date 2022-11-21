package com.alem.todolist.service;

import com.alem.todolist.model.Task;
import com.alem.todolist.model.TaskDto;
import com.alem.todolist.model.User;
import com.alem.todolist.model.UserDto;
import com.alem.todolist.myMethods;
import com.alem.todolist.repository.ToDoListRepository;
import com.alem.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;


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
    public List<UserDto> fetchAllUsers() {
        return myMethods.convertListToUserDtos(this.userRepository.findAll());
    }

    @Override
    public UserDto fetchUser(long id) {
        return new UserDto(this.userRepository.findById(id));
    }

    @Override
    public UserDto addUser(User x) {
        return new UserDto(this.userRepository.save(x));
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
    public List<TaskDto> fetchAllUserTasks(long id) {
        IntStream taskids = Arrays.stream(this.userRepository.findById(id).getUser_Tasks());
        Stream<Task> user_tasks = taskids.mapToObj(taskid -> this.toDoListRepository.findById((long) taskid).get());
        List<Task> result = user_tasks.collect(Collectors.toList());
        return myMethods.convertListToTaskDtos(result);
    }


}
