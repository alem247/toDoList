package com.alem.todolist.repository;

import com.alem.todolist.model.Task;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ToDoListRepository extends JpaRepository<Task, Long> {

    Optional<Task> findById(Long id);

    @Query(value = "SELECT * FROM tasks t WHERE DATE(t.date) between DATE(?1) and DATE(?1) + INTERVAL '1 day'", nativeQuery = true)
    List<Task> getTasksForGivenDate(Instant date);


    @Query(value = "SELECT * FROM tasks t WHERE t.group = ?1", nativeQuery = true)
    List<Task> getTasksForGivenGroup(String group);


}
