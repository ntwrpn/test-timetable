package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Faculty;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

public interface FacultyService {

    Faculty save(Faculty Faculty);

    Faculty update(Faculty Faculty);

    void delete(UUID id);

    List<Faculty> getAll();

    Optional<Faculty> getById(UUID userId);
    
    public JsonSchema getFields();
}