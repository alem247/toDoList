package com.alem.todolist.controller;


import com.alem.todolist.ObjectMapperUtils;
import com.alem.todolist.exceptions.InvalidGroupException;
import com.alem.todolist.model.TaskDto;
import com.alem.todolist.repository.ToDoListRepository;
import com.alem.todolist.model.Task;
import com.alem.todolist.repository.UserRepository;
import com.alem.todolist.service.ToDoListService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/tasks")
public class ToDoListController {
    private ToDoListRepository toDoListRepository;

    private ToDoListService toDoListService;


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public ToDoListController(ToDoListRepository toDoListRepository, ToDoListService toDoListService) {
        this.toDoListRepository = toDoListRepository;
        this.toDoListService = toDoListService;
    }

    private TaskDto convertToDto(Task task){
        return modelMapper.map(task, TaskDto.class);
    }

    private Task convertToEntity(TaskDto taskDto) throws ParseException {
        return modelMapper.map(taskDto, Task.class);
    }


    @GetMapping
    public List<TaskDto> getTasks() {
        return ObjectMapperUtils.mapAll(this.toDoListService.fetchAllTasks(), TaskDto.class);
    }

    @GetMapping(value = "/{id}")
    public TaskDto printTaskDetails(@PathVariable Long id) {
        return convertToDto(this.toDoListService.getTask(id));
    }

    @PostMapping(produces = "application/json")
    public TaskDto addTask(Task task) throws InvalidGroupException {
        return convertToDto(toDoListService.addNewTask(task));
    }

    @GetMapping(value = "/forDay/{date}")
    public List<TaskDto> printTasksForGivenDate(@PathVariable String date){
        String[] temp = date.split("_");
        String x = String.join("-", temp);
        LocalDate ld = LocalDate.parse(x);
        return ObjectMapperUtils.mapAll(toDoListService.fetchTasksByDate
                (ld.atStartOfDay(ZoneId.of("Europe/Ljubljana")).toInstant()), TaskDto.class);
    }

    @GetMapping(value ="/forGroup/{group}")
    public List<TaskDto> printTasksForGivenGroup(@PathVariable String group){
       return ObjectMapperUtils.mapAll(toDoListService.fetchTasksByGroup(group), TaskDto.class);
    }

    @DeleteMapping("/{id}")
    public void removeTask(@PathVariable("id") Long id) {
        toDoListService.removeTask(toDoListService.getTask(id));
    }
}
