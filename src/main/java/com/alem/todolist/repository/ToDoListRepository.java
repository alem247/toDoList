package com.alem.todolist.repository;

import com.alem.todolist.exceptions.MissingArgumentException;
import com.alem.todolist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface ToDoListRepository extends JpaRepository<Task, Long> {

    Optional<Task> findById(Long id);

    @Query(value = "SELECT t FROM tasks t WHERE t.date = ?1", nativeQuery = true)
    public int countTasksForGivenDate(Instant dt) throws MissingArgumentException;
}
