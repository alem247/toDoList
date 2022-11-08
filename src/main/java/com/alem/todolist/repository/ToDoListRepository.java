package com.alem.todolist.repository;

import com.alem.todolist.exceptions.MissingArgumentException;
import com.alem.todolist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface ToDoListRepository extends JpaRepository<Task, Long> {

    Optional<Task> findById(Long id);

    //@Query(value = "SELECT t FROM tasks t WHERE t.date = ?1", nativeQuery = true)
    @Query(value = "SELECT t FROM tasks t WHERE t.date between ?1 and ?1 + INTERVAL '1 day'", nativeQuery = true)
    public List<Task> getTasksForGivenDate(Instant dt) throws MissingArgumentException;


}
