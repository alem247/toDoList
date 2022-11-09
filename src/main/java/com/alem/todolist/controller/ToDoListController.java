package com.alem.todolist.controller;


import com.alem.todolist.exceptions.InvalidGroupException;
import com.alem.todolist.repository.ToDoListRepository;
import com.alem.todolist.model.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@EnableJpaRepositories("com.alem.todolist.repository")
@RestController
@RequestMapping("/tasks")
public class ToDoListController {
    private ToDoListRepository toDoListRepository;

    @Autowired
    public ToDoListController(ToDoListRepository toDoListRepository) {
        this.toDoListRepository = toDoListRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Task> getTasks() {
        return this.toDoListRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String printTaskDetails(@PathVariable Long id) {
        return this.toDoListRepository.findById(id).get().toString();
    }

    // task: id, desc, date, location, group
    @PostMapping(produces = "application/json")
    public Task addTask(Task task) throws InvalidGroupException {
        return toDoListRepository.save(task);
    }

    @RequestMapping(value = "/{YYYY_MM_DD}", method = RequestMethod.GET)
    public String printTasksForGivenDate(@PathVariable String YYYY_MM_DD){
        String[] temp = YYYY_MM_DD.split("_");
        String date = String.join("-", temp);
        LocalDate dt = LocalDate.parse(date);
        return toDoListRepository.getTasksForGivenDate(dt);
    }

    @DeleteMapping("/{id}")
    public void removeTask(@PathVariable("id") Long id) {
        toDoListRepository.deleteById(id);
    }

}
