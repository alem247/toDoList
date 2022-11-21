package com.alem.todolist.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

@Getter @Setter
@ToString @NoArgsConstructor
@Entity
@Table(name = "tasks", schema = "public")
public class Task {
    @Id
    @Column(name = "id", length = 32, nullable = false)
    private Long id;

    @Column(name = "description", length = 32, nullable = false)
    private String desc; // task description

    @Column(name = "date", nullable = false)
    private Instant date; // task due date

    @Column(name = "location", length = 32, nullable = false)
    private String location; // task location


    @Enumerated(EnumType.STRING)
    @Column(name = "group")
    private GroupType group; // work, exercise, etc.



}
