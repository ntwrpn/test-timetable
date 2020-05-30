package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Flow;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

public interface FlowService {
    
    Flow save(Flow Flow, UUID uuid);

    Flow update(Flow Flow);

    void delete(UUID id);

    List<Flow> getAll();

    Optional<Flow> getById(UUID userId);
    
    public JsonSchema getFields();

    List<Flow> findByDeaneryId(UUID uuid);

    List<Flow> findByName(String name);
}