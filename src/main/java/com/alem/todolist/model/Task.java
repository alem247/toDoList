package com.alem.todolist.model;

import lombok.*;

import javax.persistence.*;
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


    @Enumerated(EnumType.STRING)
    @Column(name = "group")
    private GroupType group; // work, exercise, etc.



}
