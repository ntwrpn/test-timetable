package com.java.repository;

import com.java.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FlowRepository extends JpaRepository<Flow, UUID> {

    Optional<Flow> findById(UUID uuid);

    void deleteById(UUID uuid);

    List<Flow> findAllByDeaneryId(UUID uuid);

    @Query("select f from Flow f JOIN f.groups gr JOIN gr.speciality s where s.lectern.id =?1 ")
    List<Flow> findFlowsByLecternId(UUID uuid);

    List<Flow> findByName(String name);
}