package com.alem.todolist.service;

import com.alem.todolist.exceptions.MissingArgumentException;
import com.alem.todolist.model.Task;
import com.alem.todolist.repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public interface ToDoListService {

     public List<Task> fetchAllTasks();

     void addNewTask(Task task);
}
