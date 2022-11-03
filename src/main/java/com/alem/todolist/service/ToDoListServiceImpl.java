package com.alem.todolist.service;

import com.alem.todolist.model.Task;
import com.alem.todolist.repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoListServiceImpl implements ToDoListService{

    private ToDoListRepository toDoListRepository;

    @Autowired
    public ToDoListServiceImpl(ToDoListRepository toDoListRepository){
        this.toDoListRepository = toDoListRepository;
    }

    @Override
    public List<Task> fetchAllTasks() {
        return this.toDoListRepository.findAll();
    }
}
