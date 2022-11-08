package com.alem.todolist.controller;


import com.alem.todolist.repository.ToDoListRepository;
import com.alem.todolist.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.Optional;
@EnableJpaRepositories("com.alem.todolist.repository")
@RestController
@RequestMapping("/tasks")
public class ToDoListController {

    private ToDoListRepository toDoListRepository;

    @Autowired
    public ToDoListController(ToDoListRepository toDoListRepository){
        this.toDoListRepository = toDoListRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Task> getTasks(){
        return this.toDoListRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String printTaskDetails(@PathVariable Long id){
        return this.toDoListRepository.findById(id).get().toString();
    }

    // task: id, desc, date, location, group
    @PostMapping(consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public void addTask(Task task){
        toDoListRepository.save(task);
    }

    @DeleteMapping("/{id}")
    public void removeTask(@PathVariable("id") Long id){
        toDoListRepository.deleteById(id);
    }

}
