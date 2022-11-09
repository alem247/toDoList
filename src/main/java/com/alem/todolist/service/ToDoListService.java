package com.alem.todolist.service;

import com.alem.todolist.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ToDoListService {

     public List<Task> fetchAllTasks();

     void addNewTask(Task task);
}
