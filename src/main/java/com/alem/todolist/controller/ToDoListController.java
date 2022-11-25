package com.alem.todolist.controller;


import com.alem.todolist.exceptions.InvalidGroupException;
import com.alem.todolist.model.TaskDto;
import com.alem.todolist.myMethods;
import com.alem.todolist.repository.ToDoListRepository;
import com.alem.todolist.model.Task;
import com.alem.todolist.service.ToDoListService;
import com.alem.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class ToDoListController {
    private ToDoListService toDoListService;

    private UserService userService;

    @Autowired
    public ToDoListController(ToDoListService toDoListService, UserService userService) {
        this.toDoListService = toDoListService;
        this.userService = userService;
    }

    @GetMapping
    public List<TaskDto> getTasks() {
        return this.toDoListService.fetchAllTasks();
    }

    @GetMapping(value = "/tasks/{id}")
    public TaskDto printTaskDetails(@PathVariable Long id) {
        return this.toDoListService.getTask(id);
    }

    @PostMapping(produces = "application/json")
    public TaskDto addTask(Task task){
        return toDoListService.addNewTask(task);
    }

    @GetMapping(value = "/tasks/forDay/{date}")
    public List<TaskDto> printTasksForGivenDate(@PathVariable String date){
        String[] date_data = date.split("_");
        String date_joined = String.join("-", date_data);
        LocalDate ld = LocalDate.parse(date_joined);
        return toDoListService.fetchTasksByDate
                (ld.atStartOfDay(ZoneId.of("Europe/Ljubljana")).toInstant());
    }

    @GetMapping(value ="/tasks/forGroup/{group}")
    public List<TaskDto> printTasksForGivenGroup(@PathVariable String group){
       return toDoListService.fetchTasksByGroup(group);
    }

    @GetMapping(value = "/tasks/forUser/{username}")
    public List<TaskDto> printTasksForGivenUser(@PathVariable String username){
        return toDoListService.fetchTasksByUser(userService.loadByUsername(username).getUser().getId());
    }

    @DeleteMapping("/tasks/{id}")
    public void removeTask(@PathVariable("id") Long id) {
        toDoListService.removeTask(toDoListService.getTask(id).getTask());
    }
}
