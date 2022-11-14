package com.alem.todolist.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "id", length = 32, nullable = false)
    private final long id;
    @Column(name = "name", length = 32, nullable = false)
    private final String name;
    @Column(name = "surname", length = 32, nullable = false)
    private final String surname;
    @Column(name = "username", length = 32, nullable = false)
    private String username;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "phonenum", length = 32, nullable = false)
    private String phonenum;
    @Column(name = "userTasks", nullable = false)
    private int[] userTasks;

}
