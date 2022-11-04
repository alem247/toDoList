package com.alem.todolist.service;

import com.alem.todolist.exceptions.MissingArgumentException;
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
        super();
        this.toDoListRepository = toDoListRepository;
    }

    @Override
    public List<Task> fetchAllTasks() {
        return this.toDoListRepository.findAll();
    }

    @Override
    public int countTasksForToday(String date) throws MissingArgumentException {
        if (date == null || date.length() < 10 || Integer.parseInt(date.substring(4, 5)) > 12){
            String message = "Enter date in format DD/MM/YYYY";
            throw new MissingArgumentException(message);
        }
        List<Task> tasks = fetchAllTasks();
        int i = 0;
        for (Task task : tasks) {
            if (task.getDate().equals(date))
                i++;
        }
          return i;
    }
}
