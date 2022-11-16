package com.alem.todolist.service;

import com.alem.todolist.model.Task;
import com.alem.todolist.repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ToDoListServiceImpl implements ToDoListService{

    private ToDoListRepository toDoListRepository;

    @Autowired
    public ToDoListServiceImpl(ToDoListRepository toDoListRepository){
        super();
        this.toDoListRepository = toDoListRepository;
    }

    @Override
    public List<Task> fetchAllTasks() {
        return this.toDoListRepository.findAll();
    }

    @Override
    public Task addNewTask(Task task){
        this.toDoListRepository.save(task);
        return task;
    }

    @Override
    public Task getTask(long id) {
        return this.toDoListRepository.findById(id).get();
    }

    @Override
    public List<Task> fetchTasksByGroup(String group) {
        return this.toDoListRepository.getTasksForGivenGroup(group);
    }

    @Override
    public List<Task> fetchTasksByDate(Instant date) {
        return this.toDoListRepository.getTasksForGivenDate(date);
    }


    @Override
    public void removeTask(Task task) {
        this.toDoListRepository.delete(task);
    }

}
