package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Groups;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

public interface GroupsService {

    Groups save(Groups Groups);

    Groups update(Groups Groups);

    void delete(UUID id);

    List<Groups> getAll();

    Optional<Groups> getById(UUID userId);
    
    public JsonSchema getFields();

    List<Groups> findByFlowLecternDeaneryId(UUID uuid);

    List<Groups> findByName(String name);

    List<Groups> findByFlowAndSpecialityLecternDeaneryId(UUID flowId,UUID uuid);
}