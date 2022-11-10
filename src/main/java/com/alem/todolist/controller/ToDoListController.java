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
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Arrays;
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


    // TODO: internal server error all of a sudden?
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String printTaskDetails(@PathVariable Long id) {
        return this.toDoListRepository.findById(id).get().toString();
    }

    // task: id, desc, date, location, group
    @PostMapping(produces = "application/json")
    public Task addTask(Task task) throws InvalidGroupException {
        return toDoListRepository.save(task);
    }

    // TODO: fix IllegalStateException
    @RequestMapping(value = "/{date}", method = RequestMethod.GET)
    public String printTasksForGivenDate(@PathVariable String date){
        String[] temp = date.split("_");
        System.out.println(Arrays.toString(temp));
        String x = String.join("-", temp);
        System.out.println(x);
        LocalDate ld = LocalDate.parse(x);
        return toDoListRepository.getTasksForGivenDate(ld.atStartOfDay(ZoneId.of("Europe/Ljubljana")).toInstant());
    }

    // TODO: fix IllegalStateException

    @RequestMapping(value="/{group}", method = RequestMethod.GET)
    public String printTasksForGivenGroup(@PathVariable String group){
       return toDoListRepository.getTasksForGivenGroup(group);
    }

    @DeleteMapping("/{id}")
    public void removeTask(@PathVariable("id") Long id) {
        toDoListRepository.deleteById(id);
    }

}
