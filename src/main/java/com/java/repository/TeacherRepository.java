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
public interface TeacherRepository extends JpaRepository<Teacher,UUID> {

    Optional<Teacher> findById(UUID uuid);

    void deleteById(UUID uuid);

    List<Teacher> findByLecternId(UUID uuid);
}