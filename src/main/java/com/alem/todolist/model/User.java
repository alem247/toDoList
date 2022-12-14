package com.alem.todolist.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@Entity
@Table(name = "users", schema = "public")
public class User {
    @Id
    @Column(name = "id", length = 32, nullable = false)
    private long id;
    @Column(name = "name", length = 32, nullable = false)
    private String name;
    @Column(name = "surname", length = 32, nullable = false)
    private String surname;
    @Column(name = "username", length = 32, nullable = false)
    private String username;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "phone_number", length = 32, nullable = false)
    private String phone_num;
    @Column(name = "user_Tasks")
    private int[] user_Tasks;

    public User() {
    }
}
