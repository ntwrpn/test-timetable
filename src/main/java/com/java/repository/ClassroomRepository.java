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
public interface ClassroomRepository extends JpaRepository<Classroom,UUID> {

    Optional<Classroom> findById(UUID uuid);

    void deleteById(UUID uuid);
    
    @Query(value = "select * from classroom where corps=?1", nativeQuery = true)
    List<Classroom> findByCorps(UUID uuid);
    
    @Query(value = "select * from classroom where lectern=?1", nativeQuery = true)
    List<Classroom> findByLectern(UUID uuid);
}