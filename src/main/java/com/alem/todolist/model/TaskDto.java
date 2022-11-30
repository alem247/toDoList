package com.alem.todolist.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.ManyToOne;
import java.time.Instant;

@Data
@AllArgsConstructor
public class TaskDto {

    private Task task;

}
