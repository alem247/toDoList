package com.alem.todolist.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "tokens", schema = "public")
public class ConfirmationToken {

    @Id
    @SequenceGenerator(name="id_token_seq",sequenceName="token_id_generator", initialValue=6, allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="id_token_seq")
    private Long id;
    private String token;

    @ManyToOne(targetEntity = User.class) // 1 user can have many tokens
    private User user;

    public ConfirmationToken
            (String token, LocalDateTime creationTime, LocalDateTime expirationTime, User user) {
        this.token = token;
        this.creationTime = creationTime;
        this.expirationTime = expirationTime;
        this.user = user;
    }

    private LocalDateTime creationTime;
    private LocalDateTime expirationTime;

    public ConfirmationToken() {

    }
}
