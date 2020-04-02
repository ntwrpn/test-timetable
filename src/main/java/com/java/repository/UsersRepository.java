package com.java.repository;

import com.java.domain.*;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<Users,UUID> {

    Optional<Users> findById(UUID uuid);

    void deleteById(UUID uuid);

    Optional<Users> findByUsername(String login);
    
    List<Users> findByEnabledTrue();
    
    List<Users> findByEnabledFalse();
    
    @Query(value = "select * from Users WHERE employee = ?1", nativeQuery = true)
    Optional<Users> findByEmployeeId(UUID uuid);

    @Query(value = "select * from Users WHERE teacher = ?1", nativeQuery = true)
    Optional<Users> findByTeacherId(UUID uuid);
    
    @Query(value = "select * from Users WHERE teacher is null and employee is null", nativeQuery = true)
    List<Users> findClear();

    @Modifying
    @Transactional
    @Query(value = "UPDATE Users SET enabled=false WHERE id = ?1", nativeQuery = true)
    void blockUser(UUID id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Users SET enabled=true WHERE id = ?1", nativeQuery = true)
    void unBlockUser(UUID id);
}