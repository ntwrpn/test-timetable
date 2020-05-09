package com.java.repository;

import com.java.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,UUID> {

    Optional<Subject> findById(UUID uuid);

    List<Subject> findAllByDepartmentAndTemplate(String department, boolean template);

    List<Subject> findAllByTemplate (boolean template);

    void deleteById(UUID uuid);
}