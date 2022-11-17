package com.alem.todolist.service;

import com.alem.todolist.model.Task;
import com.alem.todolist.model.TaskDto;
import com.alem.todolist.myMethods;
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
    public List<TaskDto> fetchAllTasks() {
        return myMethods.convertListToTaskDtos(this.toDoListRepository.findAll());
    }

    @Override
    public TaskDto addNewTask(Task task){
        this.toDoListRepository.save(task);
        return new TaskDto(task);
    }

    @Override
    public TaskDto getTask(long id) {
        return new TaskDto(this.toDoListRepository.findById(id).get());
    }

    @Override
    public List<TaskDto> fetchTasksByGroup(String group) {
        return myMethods.convertListToTaskDtos(this.toDoListRepository.getTasksForGivenGroup(group));
    }

    @Override
    public List<TaskDto> fetchTasksByDate(Instant date) {
        return myMethods.convertListToTaskDtos(this.toDoListRepository.getTasksForGivenDate(date));
    }


    @Override
    public void removeTask(Task task) {
        this.toDoListRepository.delete(task);
    }

}
