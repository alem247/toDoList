package com.alem.todolist.repository;

import com.alem.todolist.model.Task;
import com.alem.todolist.model.User;
import com.alem.todolist.model.UserDto;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
     User findById(long id);

     @Query(value = "select * from users u where u.username = ?1", nativeQuery = true)
     User findByUsername(String username);

}
