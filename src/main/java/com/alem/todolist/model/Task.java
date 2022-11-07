package com.alem.todolist.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Getter @Setter
@ToString @NoArgsConstructor
@Entity
@Table(name = "tasks", schema = "public")
public class Task {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String desc; // task description

    @Column(name = "date")
    private Instant date; // task due date

    @Column(name = "location")
    private String location; // task location

    @Column(name = "group")
    private String group; // work, exercise, etc.

}
