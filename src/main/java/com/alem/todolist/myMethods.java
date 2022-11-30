package com.alem.todolist;

import com.alem.todolist.model.Task;
import com.alem.todolist.model.TaskDto;
import com.alem.todolist.model.User;
import com.alem.todolist.model.UserDto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class myMethods {
    public static List<TaskDto> convertListToTaskDtos(List<Task> tasks){
        return tasks.stream()
                .map(TaskDto::new)
                .collect(Collectors.toList());
    }

    public static List<UserDto> convertListToUserDtos(List<User> users) {
        return users.stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

    public static List<TaskDto> convertSetToTaskDtos(Set<Task> tasks){
        return tasks.stream()
                .map(TaskDto::new)
                .collect(Collectors.toList());
    }
}
