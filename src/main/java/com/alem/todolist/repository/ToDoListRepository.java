package com.alem.todolist.repository;

import com.alem.todolist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ToDoListRepository extends JpaRepository<Task, Long> {

    Optional<Task> findById(Long id);

    @Query(value = "SELECT t FROM tasks t WHERE t.date between ?1 and ?1 + INTERVAL '1 DAY'", nativeQuery = true)
    String getTasksForGivenDate(Instant date) throws IllegalArgumentException;


    @Query(value = "SELECT t FROM tasks t WHERE t.group =: ?1", nativeQuery = true)
    String getTasksForGivenGroup(String group) throws IllegalArgumentException;


}
