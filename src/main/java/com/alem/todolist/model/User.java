package com.alem.todolist.model;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.*;
import org.aspectj.weaver.ast.Var;
import org.hibernate.hql.internal.ast.tree.IdentNode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "users", schema = "public")
public class User implements UserDetails {
    @Id
    @SequenceGenerator(name="id_seq",sequenceName="user_id_generator", initialValue=6, allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="id_seq")
    @Column(name = "id", length = 32, nullable = false)
    private long id;
    @Column(name = "name", length = 32, nullable = false)
    private String name;
    @Column(name = "surname", length = 32, nullable = false)
    private String surname;
    @Column(name = "username", length = 32, nullable = false)
    private String username;

    public User(String name, String surname, String username, String email, String password, String address, String phone_num, UserRole role) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone_num = phone_num;
        this.userRole = role;
    }

    @Column(name = "email", length = 32, nullable = false)
    private String email;
    @Column(name = "password", length = 32, nullable = false)
    private String password;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "phone_number", length = 32, nullable = false)
    private String phone_num;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 32, nullable = false)
    private UserRole userRole;

    @Column(name = "enabled", length = 32, nullable = false)
    private boolean enabled;

    @Column(name = "locked", length = 32, nullable = false)
    private boolean locked;
    @ManyToMany
    @JoinTable(
            name = "users_tasks",
            joinColumns = { @JoinColumn(name = "task_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private Collection<Task> tasks = new ArrayList<>();
    public User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
    }
    // TODO:
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    // TODO:
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    // TODO:
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    // TODO:
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
