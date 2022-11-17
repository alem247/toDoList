package com.alem.todolist.repository;

import com.alem.todolist.model.User;
import com.alem.todolist.model.UserDto;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
     User findById(long id);
}
