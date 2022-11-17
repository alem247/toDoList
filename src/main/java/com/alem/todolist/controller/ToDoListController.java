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
@RequestMapping("/tasks")
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
        return myMethods.convertListToTaskDtos(this.toDoListService.fetchAllTasks());
    }

    @GetMapping(value = "/{id}")
    public TaskDto printTaskDetails(@PathVariable Long id) {
        return new TaskDto(this.toDoListService.getTask(id));
    }

    @PostMapping(produces = "application/json")
    public TaskDto addTask(Task task) throws InvalidGroupException {
        return new TaskDto(toDoListService.addNewTask(task));
    }

    @GetMapping(value = "/forDay/{date}")
    public List<TaskDto> printTasksForGivenDate(@PathVariable String date){
        String[] date_data = date.split("_");
        String date_joined = String.join("-", date_data);
        LocalDate ld = LocalDate.parse(date_joined);
        return myMethods.convertListToTaskDtos(toDoListService.fetchTasksByDate
                (ld.atStartOfDay(ZoneId.of("Europe/Ljubljana")).toInstant()));
    }

    @GetMapping(value ="/forGroup/{group}")
    public List<TaskDto> printTasksForGivenGroup(@PathVariable String group){
       return myMethods.convertListToTaskDtos(toDoListService.fetchTasksByGroup(group));
    }

    @GetMapping(value = "/forUser/{userid}")
    public List<TaskDto> printTasksForGivenUser(@PathVariable long userid){
        return userService.fetchAllUserTasks(userid);
    }

    @DeleteMapping("/{id}")
    public void removeTask(@PathVariable("id") Long id) {
        toDoListService.removeTask(toDoListService.getTask(id));
    }
}
