package com.alem.todolist.service;

import com.alem.todolist.model.Task;
import com.alem.todolist.model.TaskDto;
import com.alem.todolist.model.User;
import com.alem.todolist.model.UserDto;
import com.alem.todolist.myMethods;
import com.alem.todolist.repository.ToDoListRepository;
import com.alem.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;


@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
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
    public UserDto loadByUsername(String username) { return new UserDto(this.userRepository.findByUsername(username));}

    @Override
    public UserDto registerUser(long id, String name, String surname, String email, String password,
                                String address, String phone_number) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPassword(password);
        user.setAddress(address);
        user.setPhone_num(phone_number);
        return new UserDto(user);
    }

    @Override
    public boolean authentication(String username, String password){
        return this.userRepository.findByUsername(username).getPassword().equals(password);
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

}
