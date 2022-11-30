package com.alem.todolist.security;

import org.springframework.context.annotation.Configuration;

import java.util.function.Predicate;

@Configuration
public class EmailValidator implements Predicate<String>{
    @Override
    public boolean test(String s) {
       // using @Email for now
        return true;
    }
}
