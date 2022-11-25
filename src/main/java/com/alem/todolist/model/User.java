package com.alem.todolist.model;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.*;
import org.aspectj.weaver.ast.Var;
import org.hibernate.hql.internal.ast.tree.IdentNode;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "users", schema = "public")
public class User {
    @Id // id, name, surname, username, email, passord, address, phone number
    @Column(name = "id", length = 32, nullable = false)
    private long id;
    @Column(name = "name", length = 32, nullable = false)
    private String name;
    @Column(name = "surname", length = 32, nullable = false)
    private String surname;
    @Column(name = "username", length = 32, nullable = false)
    private String username;
    @Column(name = "email", length = 32, nullable = false)
    private String email;
    @Column(name = "password", length = 32, nullable = false)
    private String password;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "phone_number", length = 32, nullable = false)
    private String phone_num;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "users_tasks",
            joinColumns = { @JoinColumn(name = "task_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private Collection<Task> tasks = new ArrayList<>();
    public User() {
    }
}
