package com.alem.todolist.service;

import com.alem.todolist.model.Task;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public interface ToDoListService {

     List<Task> fetchAllTasks();

     Task addNewTask(Task task);

     Task getTask(long id);

     List<Task> fetchTasksByGroup(String group);

     List<Task> fetchTasksByDate(Instant date);

     void removeTask(Task task);
}
