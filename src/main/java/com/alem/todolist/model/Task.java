package com.alem.todolist.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.ValueGenerationType;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

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
    private GroupType group; // work, exercise, etc
    @ManyToMany(mappedBy = "tasks", targetEntity = User.class)
    @JsonIgnore
    private Collection<User> users = new ArrayList<>();

}
