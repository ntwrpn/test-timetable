package com.java.repository;

import com.java.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClassroomTypeRepository extends JpaRepository<ClassroomType,UUID> {

    Optional<ClassroomType> findById(UUID Ids);

    void delete(UUID uuid);
}