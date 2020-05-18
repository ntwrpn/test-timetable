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
public interface GroupsRepository extends JpaRepository<Groups,UUID> {

    Optional<Groups> findById(UUID uuid);

    void deleteById(UUID uuid);

    List<Groups> findBySpecialityLecternDeaneryId(UUID uuid);

    List<Groups> findByName(String name);

    List<Groups> findByFlowIdAndSpecialityLecternDeaneryId(UUID flowId,UUID uuid);
}