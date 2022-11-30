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

import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/tasks")
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

    @GetMapping(value = "/{id}")
    public TaskDto printTaskDetails(@PathVariable Long id) {
        return this.toDoListService.getTask(id);
    }

    @PostMapping(value = "/addTask", produces = "application/json")
    public TaskDto addTask(Task task) {
        return toDoListService.addNewTask(task);
    }

    @GetMapping(value = "/forDay/{date}")
    public List<TaskDto> printTasksForGivenDate
            (@PathVariable @Pattern(regexp = "\"^\\\\d{4}-\\\\d{2}-\\\\d{2}$\"") String date) {
        return toDoListService.fetchTasksByDate
                (LocalDate.parse(date).atStartOfDay(ZoneId.of("Europe/Ljubljana")).toInstant());
    }

    @GetMapping(value = "/forGroup/{group}")
    public List<TaskDto> printTasksForGivenGroup(@PathVariable String group) {
        return toDoListService.fetchTasksByGroup(group);
    }

    @GetMapping(value = "/forUser/{username}")
    public List<TaskDto> printTasksForGivenUser(@PathVariable String username) {
        return userService.fetchUserTasks(username);
    }

    @DeleteMapping("/{id}")
    public void removeTask(@PathVariable("id") Long id) {
        toDoListService.removeTask(toDoListService.getTask(id).getTask());
    }
}
