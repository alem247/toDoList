package com.alem.todolist.controller;

import com.alem.todolist.model.User;
import com.alem.todolist.model.UserDto;
import com.alem.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/register_page")
    public String fetchRegisterPage(Model model){
        model.addAttribute("register_request", new UserDto(new User()));
        return "register_page";
    }

    @GetMapping("/index")
    public String fetchLoginPage(Model model){
        model.addAttribute("login_request", new UserDto(new User()));
        return "index";
    }

    @PostMapping("/register_page")
    public String registerUser(@ModelAttribute UserDto userDto){
        UserDto registeredUser = userService.registerUser(userDto.getUser().getId(), userDto.getUser().getName(), userDto.getUser().getSurname(),
                userDto.getUser().getEmail(), userDto.getUser().getPassword(), userDto.getUser().getAddress(),
                userDto.getUser().getPhone_num());
        return "redirect:/index";
    }

    @PostMapping("/index")
    public String loginUser(@ModelAttribute UserDto userDto){
        boolean login = userService.authentication(userDto.getUser().getUsername(), userDto.getUser().getPassword());
        if (login)
            return "redirect:/api/tasks";
        else
            return "error_page";
    }



}
