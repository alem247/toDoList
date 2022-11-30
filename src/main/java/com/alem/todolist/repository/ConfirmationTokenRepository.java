package com.alem.todolist.repository;

import com.alem.todolist.model.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    ConfirmationToken findByToken(String token);
    @Transactional
    @Modifying
    @Query(value = "UPDATE tokens SET confirmation_time = ?2 WHERE token = ?1", nativeQuery = true)
    int updateConfirmationTime(String token, LocalDateTime newConfirmationTime);
}
