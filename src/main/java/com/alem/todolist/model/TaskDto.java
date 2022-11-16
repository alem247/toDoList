package com.alem.todolist.model;

import lombok.Data;

import java.time.Instant;

@Data
public class TaskDto {

    private Long id;
    private String desc;
    private Instant date;
    private String location;
    private String group;
}
