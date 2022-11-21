package com.alem.todolist.service;

import com.alem.todolist.model.Task;
import com.alem.todolist.model.TaskDto;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public interface ToDoListService {

     List<TaskDto> fetchAllTasks();

     TaskDto addNewTask(Task task);

     TaskDto getTask(long id);

     List<TaskDto> fetchTasksByGroup(String group);

     List<TaskDto> fetchTasksByDate(Instant date);

     void removeTask(Task task);
}
