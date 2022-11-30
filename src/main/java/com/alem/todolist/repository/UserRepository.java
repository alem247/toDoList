package com.alem.todolist.repository;

import com.alem.todolist.model.Task;
import com.alem.todolist.model.User;
import com.alem.todolist.model.UserDto;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
     User findById(long id);

     @Query(value = "select * from users u where u.username = ?1", nativeQuery = true)
     User findByUsername(String username);

     @Query(value = "SELECT u.task_id from users_tasks u where u.user_id = ?1 ", nativeQuery = true)
     List<Long> getTasksForUser(long id);


     @Query(value = "SELECT u from users u where u.email = ?1", nativeQuery = true)
     User findByEmail(String email);

     @Transactional
     @Modifying
     @Query(value = "UPDATE users SET enabled = true WHERE email = ?1", nativeQuery = true)
     void enableUser(String email);

}
